package ch.zhaw.psit.towerhopscotch.models.maps;

import ch.zhaw.psit.towerhopscotch.models.enums.LayerType;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents the whole map with it layers
 * @author Nicolas Eckhart, Stefan Bösch
 */
public class Map {

    private Layer hell;
    private Layer earth;
    private Layer heaven;

    public Map(String filePath) {
        initializeMap(filePath);
    }

    /**
     * Update the layers
     * @param absNanoTime absNanoTime
     */
    public void update(long absNanoTime) {
        hell.update(absNanoTime);
        earth.update(absNanoTime);
        heaven.update(absNanoTime);
    }

    /**
     * Render the layers
     * @param g Graphics
     */
    public void render(Graphics g) {
        hell.render(g);
        earth.render(g);
        heaven.render(g);
    }

    /**
     * Get the layer at the specified point
     * @param point Point on map
     * @return Layer where the point is located
     */
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

    /**
     * Initialize the map and the layers
     * @param filePath the path to the map file
     */
    private void initializeMap(String filePath) {
        int width = 0;
        int height = 0;
        StringBuilder fileContents = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)));
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

    /**
     * Check if point is on one of the layers
     * @param point Point
     * @return If the point is on a layer
     */
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
}
