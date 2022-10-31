package gameObjects.helper;

import gameObjects.ReefDisplay;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Resource {
    private static Map<String, BufferedImage> resources;

    static {
        Resource.resources = new HashMap<>();
        try {
            Resource.resources.put("title", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("title.png"))));
            Resource.resources.put("life", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("life.png"))));
            Resource.resources.put("player", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("player.png"))));
            Resource.resources.put("boss_Monica", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Monica.png"))));
            Resource.resources.put("boss_Matt", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Matt.png"))));
            Resource.resources.put("boss_Fara", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Fara.png"))));
            Resource.resources.put("boss_Chloe", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Chloe.png"))));
            Resource.resources.put("boss_Bryce", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Bryce.png"))));
            Resource.resources.put("boss_Ama", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("boss_Ama.png"))));
            Resource.resources.put("ball", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("ball.png"))));
            Resource.resources.put("unbreak", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("unbreak.png"))));
            Resource.resources.put("tile5", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("tile5.png"))));
            Resource.resources.put("tile4", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("tile4.png"))));
            Resource.resources.put("tile3", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("tile3.png"))));
            Resource.resources.put("tile2", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("tile2.png"))));
            Resource.resources.put("tile1", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("tile1.png"))));
            Resource.resources.put("extraPointTile", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("extraPointTile.png"))));
            Resource.resources.put("healthTile", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("healthTile.png"))));
            Resource.resources.put("slowSpeedTile", read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("slowSpeedTile.png"))));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key){
        return Resource.resources.get(key);
    }

}