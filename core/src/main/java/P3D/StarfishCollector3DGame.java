package P3D;

public class StarfishCollector3DGame extends BaseGame {

    @Override
    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}
