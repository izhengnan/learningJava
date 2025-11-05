package main;

import javax.swing.*;

public class GameJFrame extends JFrame {
    public GameJFrame() {
        initJFrame();
        initJMenuBar();
        initImage();
        this.setVisible(true);
    }
    public void initImage(){
        int count=1;
        for(int j=1;j<=4;j++) {
            for (int i = 1; i <= 4; i++) {
                String fileName = "C:\\Users\\16048\\Documents\\JAVA\\11.19\\puzzlegame\\image\\animal\\animal1\\" + count + ".jpg";
                ImageIcon icon = new ImageIcon(fileName);
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(105 * (i-1), (j-1)*105, 105, 105);
                this.getContentPane().add(jLabel);
                count++;
            }
        }
    }
    public void initJFrame(){
        this.setSize(603, 680);
        this.setTitle("拼图游戏");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
    }
    public void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        menuBar.add(functionMenu);
        menuBar.add(aboutMenu);

        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);

        aboutMenu.add(accountItem);

        this.setJMenuBar(menuBar);
    }
}
