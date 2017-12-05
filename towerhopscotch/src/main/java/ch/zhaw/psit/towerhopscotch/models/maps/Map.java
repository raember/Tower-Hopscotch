package ch.zhaw.psit.towerhopscotch.models.maps;

import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {

    private Layer hell;
    private Layer earth;
    private Layer heaven;

    public Map(String filePath) {
        initializeMap(filePath);
    }

    public void update(long absNanoTime) {
        hell.update(absNanoTime);
        earth.update(absNanoTime);
        heaven.update(absNanoTime);
    }

    public void render(Graphics g) {
        hell.render(g);
        earth.render(g);
        heaven.render(g);
    }

    public Layer getLayer(Point point) {
        if (hell.isOnLayer(point)) {
            return hell;
        }
        if (earth.isOnLayer(point)) {
            return earth;
        }
        if (heaven.isOnLayer(point)) {
            return heaven;
        }
        return null;
    }

    private void initializeMap(String filePath) {
        int width = 0;
        int height = 0;
        StringBuilder fileContents = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            line = br.readLine();
            String[] tokens = line.split("\\s+");
            width = Integer.parseInt(tokens[0]);
            height = Integer.parseInt(tokens[1]);

            while ((line = br.readLine()) != null) {
                line = line + "\n";
                fileContents.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] layerContents = fileContents.toString().split("-");

        hell = new Layer(LayerType.HELL, width, height, layerContents[1]);
        earth = new Layer(LayerType.EARTH, width, height, layerContents[2]);
        heaven = new Layer(LayerType.HEAVEN, width, height, layerContents[3]);
    }

    public Layer[] getOtherLayers(LayerType layerType) {
        Layer[] layers = new Layer[2];
        switch (layerType) {
            case HELL:
                layers[0] = earth;
                layers[1] = heaven;
                break;
            case EARTH:
                layers[0] = hell;
                layers[1] = heaven;
                break;
            case HEAVEN:
                layers[0] = hell;
                layers[1] = earth;
                break;
        }
        return layers;

    }

    public boolean isOnMap(Point point) {
        return hell.isOnLayer(point) || earth.isOnLayer(point) || heaven.isOnLayer(point);
    }

    public Layer getHell() {
        return hell;
    }

    public Layer getEarth() {
        return earth;
    }

    public Layer getHeaven() {
        return heaven;
    }

    private Player getPlayer() {
        return ((GameState) State.getState()).getPlayer();
    }
}
