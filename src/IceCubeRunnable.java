import javafx.scene.text.Text;
public class IceCubeRunnable implements Runnable
{
    private Text iceCubeTextHolder;
    private int iceCubeAmount;
    public IceCubeRunnable(Text x)
    {
        iceCubeTextHolder = x;
    }
    @Override
    public void run()
    {
        //does nothing so far
        for(int i = 0; i < 1000000; i++)
        {
            System.out.println(Main.getIceCubes());
//            iceCubeAmount = Main.getIceCubes();
            iceCubeTextHolder.setText(""+ Main.getIceCubes());
            System.out.println(iceCubeTextHolder);
            System.out.println("i am running on " + i + " iteration");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

