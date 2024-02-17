import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private String audioStatus;
    private String filename;
    private Clip mainClip;
    private long currentFrame;
    private FloatControl gainControl;
    private double gain;
    private float dB;

    public Audio(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        mainClip = audioPlayer(filename);
        mainClip.loop(Clip.LOOP_CONTINUOUSLY);
        audioStatus = "looping";
        // set the initial gain value
        gainControl = (FloatControl) mainClip.getControl(FloatControl.Type.MASTER_GAIN);
        gain = 0.5; // set to 0.0 for initial volume
        dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        mainClip.start();
        audioStatus = "playing";
    }

    public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (audioStatus.equals("paused")) {
            System.out.println("audio is already paused");
        } else {
            currentFrame = mainClip.getMicrosecondPosition();
            mainClip.stop();
            audioStatus = "paused";
        }
    }

    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (audioStatus.equals("playing")) {
            System.out.println("Audio is already being played");
        } else {
            mainClip.close();
            mainClip.setMicrosecondPosition(currentFrame);
            play();
        }
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        currentFrame = 0L;
        mainClip.stop();
        mainClip.close();
        audioStatus = "stopped";
    }

    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        mainClip.stop();
        mainClip.close();
        currentFrame = 0L;
        mainClip.setMicrosecondPosition(currentFrame);
        play();
    }

    public static Clip audioPlayer(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File file = new File(filename);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    public ImageIcon imageSet(String filename) throws java.io.IOException
    {
        BufferedImage image = ImageIO.read(new File(filename));
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

    public void changeSong(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        mainClip.stop();
        mainClip = audioPlayer(filename);
        currentFrame = 0L;
    }

    public Clip getMainClip() {
        return mainClip;
    }

    public void setMainClip(Clip mainClip) {
        this.mainClip = mainClip;
    }

    public String getAudioStatus() {
        return audioStatus;
    }

    public FloatControl getGainControl()
    {
        return gainControl;
    }
    public double getGain()
    {
        return gain;
    }
    public float getdB()
    {
        return dB;
    }
    public void setdB(float x)
    {
        dB = x;
    }
    public void setGain(double x)
    {
        gain = x;
    }

}