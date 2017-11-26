package ch.zhaw.psit.towerhopscotch.maps;

import ch.zhaw.psit.towerhopscotch.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {

    private Game game;
    private Layer hell;
    private Layer earth;
    private Layer heaven;

    public Map(Game game, String filePath) {
        this.game = game;
        initializeMap(filePath);
    }

    public void update() {
        hell.update();
        earth.update();
        heaven.update();
    }

    public void render(Graphics g) {
        hell.render(g);
        earth.render(g);
        heaven.render(g);
    }

    public boolean isPath(float x, float y) {
        return hell.isPath(x,y) || earth.isPath(x,y) || heaven.isPath(x,y);
    }

    public boolean isFortress(float x, float y) {
        return hell.isFortress(x,y) || earth.isFortress(x,y) || heaven.isFortress(x,y);
    }

    public boolean isBeneathMap(float x, float y) {
        return hell.isBeneathMap(x,y) || earth.isBeneathMap(x,y) || heaven.isBeneathMap(x,y);
    }

    private boolean isOnMap(float x, float y) {
        return hell.isOnLayer(x,y) || earth.isOnLayer(x,y) || heaven.isOnLayer(x,y);
    }

    private void initializeMap(String filePath) {
        int width = 0;
        int height = 0;
        StringBuilder fileContents = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            line = br.readLine();
            String[] tokens = line.toString().split("\\s+");
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

        hell = new Layer(game, 1,width,height,layerContents[1],10);
        earth = new Layer(game, 2,width,height,layerContents[2],10);
        heaven = new Layer(game, 3,width,height,layerContents[3],10);

    }
}
