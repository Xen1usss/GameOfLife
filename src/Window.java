import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements Runnable {

    JFrame frame;
    Box[][] boxes;

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * Config.WIDTH, Config.HEIGHT * Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Game of life");
    }

    void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }

        for (int x = 0; x < Config.WIDTH; x++) //суть: выбираем конкретную клетку на экране,
            for (int y = 0; y < Config.HEIGHT; y++) {
                for (int sx = -1; sx <= +1; sx++)  //вокруг этой клетки проверяем 8 клеток,
                    for (int sy = -1; sy <= +1; sy++)
                        if (!(sx == 0) && (sy == 0))
                            boxes[x][y].cell.addNear(boxes[  //каждую клетку вокруг добавляем в качестве соседа
                                    (x + sx + Config.WIDTH) % Config.WIDTH]
                                    [(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);
                for (x = 10; x < 15; x++)
                    boxes[x][10].cell.status = Status.LIVE;
                boxes[x][10].setColor();
            }

    }

    private void initTimer() {
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Config.SLEEPMS, t1);
        timer.start();
    }

    private class TimerListener implements ActionListener {

        boolean flop = false;

        @Override
        public void actionPerformed(ActionEvent e) { //вызываем для каждой ячейки следующий этап
            flop = !flop;
            for (int x = 0; x < Config.WIDTH; x++)
                for (int y = 0; y < Config.HEIGHT; y++) {
                    if (flop)
                    boxes[x][y].step1();
                    else
                    boxes[x][y].step2();

                }
        }
    }
}
