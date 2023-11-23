import javax.sound.sampled.*;

public class Music {
    private Clip clip;
    private int pause;

    public Music(String musicFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(musicFile));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.close();
            clip.setFramePosition(0);
            clip = null;
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            pause = clip.getFramePosition();
            clip.stop();
        }
    }

    public void unpause() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(pause);
            clip.start();
        }
    }
}
