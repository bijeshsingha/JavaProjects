import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements KeyListener {
    void print() {
        System.out.println("Hello");
    }

    String input = "Hello";
    List<JLabel> list = new ArrayList<>();

    JPanel createPanel(String value) {
        JPanel panel;
        JLabel jLabel1;
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(204, 204, 255));
        panel.setPreferredSize(new java.awt.Dimension(100, 100));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addContainerGap()));

        list.add(jLabel1);
        return panel;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    Game() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(new java.awt.GridLayout(4, 4));

        int arr[][] = new int[4][4];
        createBase(arr);

        int i1, i2, i3, i4, i, j;
        int a[][] = new int[4][4];
        int tmp[][] = new int[4][4];
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                a[i][j] = 0;
                tmp[i][j] = 0;
            }
        }
        i1 = getRandomNumber(0, 3);
        i2 = getRandomNumber(0, 3);

        while (true) {
            i3 = getRandomNumber(0, 3);
            i4 = getRandomNumber(0, 3);
            if (i3 != i1 && i4 != i2)
                break;
        }

        a[i1][i2] = 2;
        a[i3][i4] = 4;

        updateBoard(a);
        addKeyListener(new java.awt.event.KeyAdapter() {
            int k = a[0][0];

            public void keyPressed(java.awt.event.KeyEvent evt) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        tmp[i][j] = a[i][j];
                    }
                }
                input = formKeyPressed(evt);
                k = k + 1;
                if (input == "UP") {
                    upmove(a);
                }
                if (input == "DOWN") {
                    downmove(a);
                }
                if (input == "LEFT") {
                    leftmove(a);
                }
                if (input == "RIGHT") {
                    rightmove(a);
                }

                if (check(tmp, a) == 0) {
                    addblock(a);
                }

                updateBoard(a);

                if (checkover(a) == 0) {
                    dispose();
                    new GameOver();
                }
            }

            int checkover(int a[][]) {
                int i, j;
                boolean fl = false;
                for (i = 0; i < 4; i++)
                    for (j = 0; j < 4; j++)
                        if (a[i][j] == 0) {
                            fl = true;
                            break;
                        }

                boolean gl = false;
                for (i = 0; i < 3; i++)
                    for (j = 0; j < 3; j++)
                        if (a[i + 1][j] == a[i][j] || a[i][j + 1] == a[i][j]) {
                            gl = true;
                            break;
                        }

                if (fl || gl) {
                    return 1;
                } else {
                    return 0;
                }
            }

            void upmove(int a[][]) {
                int i, j, li, ri;
                for (j = 0; j < 4; j++) {
                    li = 0;
                    ri = j;
                    for (i = 1; i < 4; i++) {
                        if (a[i][j] != 0) {
                            if (a[i - 1][j] == 0 || a[i - 1][j] == a[i][j]) {
                                if (a[li][ri] == a[i][j]) {
                                    a[li][ri] *= 2;
                                    a[i][j] = 0;
                                } else {
                                    if (a[li][ri] == 0) {
                                        a[li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    } else {
                                        a[++li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    }
                                }
                            } else
                                li++;
                        }
                    }
                }
            }

            void downmove(int a[][]) {
                int i, j, li, ri;
                for (j = 0; j < 4; j++) {
                    li = 3;
                    ri = j;
                    for (i = 2; i >= 0; i--) {
                        if (a[i][j] != 0) {
                            if (a[i + 1][j] == 0 || a[i + 1][j] == a[i][j]) {
                                if (a[li][ri] == a[i][j]) {
                                    a[li][ri] *= 2;
                                    a[i][j] = 0;
                                } else {
                                    if (a[li][ri] == 0) {
                                        a[li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    } else {
                                        a[--li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    }
                                }
                            } else
                                li--;
                        }
                    }
                }
            }

            void leftmove(int a[][]) {
                int i, j, li, ri;
                for (i = 0; i < 4; i++) {
                    li = i;
                    ri = 0;
                    for (j = 1; j < 4; j++) {
                        if (a[i][j] != 0) {
                            if (a[i][j - 1] == 0 || a[i][j - 1] == a[i][j]) {
                                if (a[li][ri] == a[i][j]) {
                                    a[li][ri] *= 2;
                                    a[i][j] = 0;
                                } else {
                                    if (a[li][ri] == 0) {
                                        a[li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    } else {
                                        a[li][++ri] = a[i][j];
                                        a[i][j] = 0;
                                    }
                                }
                            } else
                                ri++;
                        }
                    }
                }
            }

            void rightmove(int a[][]) {
                int i, j, li, ri;
                for (i = 0; i < 4; i++) {
                    li = i;
                    ri = 3;
                    for (j = 2; j >= 0; j--) {
                        if (a[i][j] != 0) {
                            if (a[i][j + 1] == 0 || a[i][j + 1] == a[i][j]) {
                                if (a[li][ri] == a[i][j]) {
                                    a[li][ri] *= 2;
                                    a[i][j] = 0;
                                } else {
                                    if (a[li][ri] == 0) {
                                        a[li][ri] = a[i][j];
                                        a[i][j] = 0;
                                    } else {
                                        a[li][--ri] = a[i][j];
                                        a[i][j] = 0;
                                    }
                                }
                            } else
                                ri--;
                        }
                    }
                }
            }

        });


        // list.get(2).setText("2");
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void addblock(int[][] a) {
        int li, ri;
        while (true) {
            li = getRandomNumber(0, 3);
            ri = getRandomNumber(0, 3);
            if (a[li][ri] == 0) {
                a[li][ri] = (int) Math.pow(2, li % 2 + 1);
                break;
            }
        }
    }

    int check(int[][] tmp, int[][] a) {
        int fl = 1, i, j;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++)
                if (tmp[i][j] != a[i][j]) {
                    fl = 0;
                    break;
                }
        return fl;
    }

    private String formKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            return "UP";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            return "DOWN";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            return "RIGHT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            return "LEFT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.out.println("ESCAPE");
            dispose();
        }
        return null;
    }

    private void createBase(int[][] arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String value = Integer.toString(arr[i][j]);
                JPanel panel = createPanel(value);
                getContentPane().add(panel);
            }
        }
    }

    private void updateBoard(int[][] arr) {
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    list.get(k).setText(" ");
                } else {
                    list.get(k).setText(Integer.toString(arr[i][j]));
                }
                k++;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            input = "UP";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            input = "DOWN";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            input = "RIGHT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            input = "LEFT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.out.println("ESCAPE");
            dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        // TODO Auto-generated method stub
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            input = "UP";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            input = "DOWN";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            input = "RIGHT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            input = "LEFT";
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.out.println("ESCAPE");
            dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
