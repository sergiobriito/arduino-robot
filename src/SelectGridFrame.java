import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SelectGridFrame extends JFrame implements ActionListener {
    private final int gridSize = 10;
    private final JButton[][] squares;
    private final int[][] grid;

    private final JButton returnButton;
    private final JButton selectStartButton;
    private final JButton selectEndButton;
    private final JButton selectObstaclesButton;

    private int[] startPoint;
    private int[] endPoint;

    private boolean selectingObstacles;
    private boolean selectingStartPoint;
    private boolean selectingEndPoint;

    public SelectGridFrame() {
        super("Mapeamento de rota");

        squares = new JButton[gridSize][gridSize];
        grid = new int[gridSize][gridSize];

        JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBackground(Color.WHITE);
                squares[i][j].addActionListener(this);
                gridPanel.add(squares[i][j]);
            }
        }

        returnButton = new JButton("Processar");
        returnButton.addActionListener(this);

        selectStartButton = new JButton("Ponto inicial");
        selectStartButton.addActionListener(this);

        selectEndButton = new JButton("Ponto final");
        selectEndButton.addActionListener(this);

        selectObstaclesButton = new JButton("Obstaculos");
        selectObstaclesButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(selectStartButton);
        buttonPanel.add(selectEndButton);
        buttonPanel.add(selectObstaclesButton);
        buttonPanel.add(returnButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        if (sourceButton == returnButton) {
            dispose();
            StringBuilder directionsMessage = null;
            if (startPoint != null && endPoint != null) {
                SearchAlgorithm searchAlgorithm = new SearchAlgorithm(grid);
                String[] directions = searchAlgorithm.findPath(startPoint, endPoint);
                directionsMessage = new StringBuilder();

                for (String direction : directions) {
                    directionsMessage.append(direction);
                }
            }
            SendMessageFrame sendMessageFrame = new SendMessageFrame(directionsMessage.toString());

        } else if (sourceButton == selectStartButton) {
            resetButtonColors();
            selectStartButton.setBackground(Color.GREEN);
            selectingStartPoint = true;
            selectingObstacles = false;
            selectingEndPoint = false;
        } else if (sourceButton == selectEndButton) {
            resetButtonColors();
            selectEndButton.setBackground(Color.RED);
            selectingEndPoint = true;
            selectingObstacles = false;
            selectingStartPoint = false;
        } else if (sourceButton == selectObstaclesButton) {
            resetButtonColors();
            selectObstaclesButton.setBackground(Color.BLACK);
            selectingObstacles = true;
            selectingStartPoint = false;
            selectingEndPoint = false;
        } else {
            JButton selectedSquare = (JButton) e.getSource();

            int selectedRow = -1;
            int selectedCol = -1;
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (selectedSquare == squares[i][j]) {
                        selectedRow = i;
                        selectedCol = j;
                        break;
                    }
                }
            }

            if (selectingObstacles) {
                setObstacle(selectedRow, selectedCol);
            } else if (selectingStartPoint){
                setStartPoint(selectedRow, selectedCol);
            }else if (selectingEndPoint){
                setEndPoint(selectedRow, selectedCol);
            };
        }
    }

    public void setObstacle(int row, int col) {
        JButton selectedSquare = squares[row][col];
        Color selectedColor = selectedSquare.getBackground();
        if (selectedColor == Color.BLACK) {
            selectedSquare.setBackground(Color.WHITE);
            grid[row][col] = 0;
        } else {
            selectedSquare.setBackground(Color.BLACK);
            grid[row][col] = 1;
        }
    }

    public void setStartPoint(int row, int col) {
        JButton selectedSquare = squares[row][col];
        Color selectedColor = selectedSquare.getBackground();
        if (selectedColor == Color.GREEN) {
            selectedSquare.setBackground(Color.WHITE);
            startPoint = new int[] {-1,-1};
        }else{
            selectedSquare.setBackground(Color.GREEN);
            startPoint = new int[] {row,col};
        };
    };

    public void setEndPoint(int row, int col) {
        JButton selectedSquare = squares[row][col];
        Color selectedColor = selectedSquare.getBackground();
        if (selectedColor == Color.RED) {
            selectedSquare.setBackground(Color.WHITE);
            endPoint = new int[] {-1,-1};
        } else{
            selectedSquare.setBackground(Color.RED);
            endPoint = new int[] {row,col};
        };
    }

    public void resetButtonColors() {
        selectStartButton.setBackground(null);
        selectEndButton.setBackground(null);
        selectObstaclesButton.setBackground(null);
    }

}
