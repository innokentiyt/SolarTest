package com.solar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class Body {
    private String textureName;
    private float imgRotationSpeed;
    private float distance;
    private float diameter;
    private float angle;
    private float movingSpeed;
    private final Body parent;
    private final List<Body> childs = new ArrayList<Body>();
    private Texture texture;
    private Image image;

    //конструктор солнца
    public Body() {
        this.textureName = "badlogic.jpg";
        this.imgRotationSpeed = 2;
        this.distance = 0;
        this.diameter = 80;
        this.angle = 0;
        this.movingSpeed = 0;
        this.parent = null;
        this.texture = new Texture(Gdx.files.internal(textureName));
        this.image = new Image(this.texture);
        this.image.setWidth(this.diameter);
        this.image.setHeight(this.diameter);
        this.image.setPosition(SolarSystemTest.WIDTH/2 - diameter/2, SolarSystemTest.HEIGHT/2 - diameter/2);
        this.image.setOrigin(-300, -300);
        this.image.setRotation(-30);
        //this.image.setRotation(imgRotationSpeed);
        SolarSystemTest.stage.addActor(this.image);
}

    //конструктор остальных тел
    public Body(String textureName, float imgRotationSpeed, float distance, float diameter, float angle, float movingSpeed, Body parent) {
        this.textureName = textureName;
        this.imgRotationSpeed = imgRotationSpeed;
        this.distance = distance;
        this.diameter = diameter;
        this.angle = angle;
        this.movingSpeed = movingSpeed;
        this.parent = parent;

        parent.addChild(this);
    }

    private void addChild(Body child) {
        childs.add(child);
    }

    public Body getParent() {
        return parent;
    }


}
