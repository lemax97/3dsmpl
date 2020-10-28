package P3D;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix4;

public class BaseActor3D {

    private ModelInstance modelData;
    private final Vector3 position;
    private final Quaternion rotation;
    private final Vector3 scale;
    protected Stage3D stage;

    public BaseActor3D(float x, float y, float z, Stage3D s) {

        modelData = null;
        this.position = new Vector3(x,y,z);
        this.rotation = new Quaternion();
        this.scale = new Vector3(1,1,1);
        this.stage = s;
        s.addActor(this);
    }

    public void setModelData(ModelInstance m) {
        this.modelData = m;
    }

    public Matrix4 calculateTransform(){

        return new Matrix4(position, rotation, scale);
    }

    public void setColor(Color c){

        for (Material m : modelData.materials )
            m.set( ColorAttribute.createDiffuse(c) );
    }

    public void loadTexture(String fileName) {

        Texture tex = new Texture(Gdx.files.internal(fileName), true);
        tex.setFilter( TextureFilter.Linear, TextureFilter.Linear);

        for (Material m : modelData.materials)
            m.set( TextureAttribute.createDiffuse(tex) );
    }

    public void act(float dt) {

        modelData.transform.set( calculateTransform() );
    }

    public void draw(ModelBatch batch, Environment env){

        batch.render(modelData, env);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 v) {

        position.set(v);
    }

    public void setPosition(float x, float y, float z){

        position.set(x, y, z);
    }

    public void moveBy(Vector3 v) {

        position.add(v);
    }

    public void moveBy(float x, float y, float z) {

        moveBy( new Vector3(x, y, z) );
    }

    public float getTurnAngle() {

        return rotation.getAngleAround(0, -1, 0);
    }

    public void setTurnAngle(float degrees) {

        rotation.set( new Quaternion(Vector3.Y, degrees) );
    }

    public void turn(float degrees) {

        rotation.mul( new Quaternion(Vector3.Y, -degrees));
    }

    public void moveForward(float dist){

        moveBy( rotation.transform( new Vector3(0, 0, -1)).scl( dist ));
    }

    public void moveUp(float dist){

        moveBy( rotation.transform( new Vector3(0, 1, 0)).scl( dist ));
    }

    public void moveRight(float dist){

        moveBy( rotation.transform( new Vector3(1, 0, 0)).scl( dist ));
    }

    public void setScale(float x, float y, float z){

        scale.set(x, y, z);
    }
}
