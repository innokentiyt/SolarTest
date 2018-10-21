package com.solar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.concurrent.ThreadLocalRandom;

public class Body extends SolarSystemTest {
    private String textureName; //имя файла с текстурой
    private float imgRotationSpeed; //скорость поворота тела относительно своего центра
    private float distance; //расстояние от центра солнца
    private float diameter; //диаметр тела
    private float angle; //угол начального положения тела
    private float movingSpeed; //скорость перемещения по орбите
    private Body parent; //тело, вокруг которого осуществляется орбитальное вращение
    private Texture texture;
    private Image image;

    //метод для установки начальных параметров тела
    private void setInitialParams (String textureName, float imgRotationSpeed, float distance, float diameter, float angle, float movingSpeed, Body parent) {
        this.textureName = textureName;
        this.imgRotationSpeed = imgRotationSpeed;
        this.distance = distance;
        this.diameter = diameter;
        this.angle = angle;
        this.movingSpeed = movingSpeed;
        this.parent = parent;

        this.texture = new Texture(Gdx.files.internal(textureName));
        this.image = new Image(this.texture);
        this.image.setWidth(this.diameter);
        this.image.setHeight(this.diameter);
        this.image.setName(textureName);

        this.image.setOrigin(diameter/2, diameter/2);
        //рандомизация угла поворота относительно центра тела
        this.image.setRotation(ThreadLocalRandom.current().nextInt(0, 360 + 1));
    }

    //конструктор солнца
    public Body() {
        setInitialParams(
                "sun.png",
                5,
                0,
                100,
                0,
                0,
                null
        );

        this.image.setPosition(SolarSystemTest.WIDTH/2 - diameter/2, SolarSystemTest.HEIGHT/2 - diameter/2);

        //стандартная неизменяемая анимация для солнца
        ParallelAction sunRotation = new ParallelAction();
        sunRotation.addAction(Actions.rotateBy(imgRotationSpeed, 1));
        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(sunRotation);
        this.image.addAction(infiniteLoop);

        SolarSystemTest.stage.addActor(this.image);
    }

    //конструктор остальных тел
    public Body(String textureName, float imgRotationSpeed, float distance, float diameter, float angle, float movingSpeed, Body parent) {
        setInitialParams(
                textureName,
                imgRotationSpeed,
                distance,
                diameter,
                angle,
                movingSpeed,
                parent
        );

        this.image.setPosition(parent.getX() + distance*(float)Math.cos(angle) - diameter/2, parent.getY() + distance*(float)Math.sin(angle) - diameter/2);
        //System.out.printf(this.getClass().getSimpleName()+ " %.0f ", parent.getX());
        //System.out.printf(this.getClass().getSimpleName()+ " %.0f ", parent.getY());

        final ParallelAction bodyAnimation = new ParallelAction();
        bodyAnimation.addAction(Actions.rotateBy(imgRotationSpeed*movingSpeed, movingSpeed));
        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(bodyAnimation);
        this.image.addAction(infiniteLoop);

        TemporalAction moveAnimation = new TemporalAction(360, Interpolation.linear) {
            @Override
            protected void update(float percent) {
                bodyAnimation.addAction(updateAnimation(percent));
            }

            @Override
            public void restart() {

            }
        };

        this.image.addAction(moveAnimation);

        SolarSystemTest.stage.addActor(this.image);
    }

    public float getX() {
        return this.image.getX() + diameter/2;
    }

    public float getY() {
        return this.image.getY() + diameter/2;
    }

    Action updateAnimation(float percent) {
        return Actions.moveTo(
                parent.getX() + (distance) * (float) Math.cos(angle + 360 * (percent*movingSpeed/100)) - diameter / 2,
                parent.getY() + (distance) * (float) Math.sin(angle + 360 * (percent*movingSpeed/100)) - diameter / 2
        );
    }
}

