package Controllers;

import Backend.*;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import  javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class BoardController {
    @FXML
    GridPane Board;


    public void initialize() {
        generateBoard();
    }

    public void generateBoard() {

        ///Pawn initialization
        WhitePawn [] Whitepawns = new WhitePawn[8];
        ImageView [] WhitePawns = new ImageView[8];
        BlackPawn [] Blackpawns = new BlackPawn[8];
        ImageView [] BlackPawns = new ImageView[8];

        for(int i=0;i<8;i++)
        {
            Whitepawns[i] = new WhitePawn("images/White_Pawn.png",Piece.Colour.White);
            WhitePawns[i] = new ImageView(Whitepawns[i].getPieceImage());
            Blackpawns[i]= new BlackPawn("images/Black_Pawn.png",Piece.Colour.Black);
            BlackPawns[i] = new ImageView(Blackpawns[i].getPieceImage());
        }

        ///Black Queen initialization
        Queen Blackqueen = new Queen("images/Black_Queen.png", Queen.Colour.Black);
        ImageView BlackQueen = new ImageView(Blackqueen.getPieceImage());

        ///White Queen initialization
        Queen Whitequeen = new Queen("images/White_Queen.png", Queen.Colour.White);
        ImageView WhiteQueen = new ImageView(Whitequeen.getPieceImage());

        /// White Bishop initialization
        Bishop [] Whitebishop = new Bishop[2];
        ImageView [] WhiteBishop = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Whitebishop[i] = new Bishop("images/White_Bishop.png",Piece.Colour.White);
            WhiteBishop[i] = new ImageView(Whitebishop[i].getPieceImage());
        }

        ///black bishop initialization
        Bishop [] Blackbishop = new Bishop[2];
        ImageView [] BlackBishop = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Blackbishop[i] = new Bishop("images/Black_Bishop.png",Piece.Colour.Black);
            BlackBishop[i] = new ImageView(Blackbishop[i].getPieceImage());
        }

        ///White Rook initialization
        Rook [] Whiterook = new Rook[2];
        ImageView [] WhiteRook = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Whiterook[i] = new Rook("images/White_Rook.png",Piece.Colour.White);
            WhiteRook[i] = new ImageView(Whiterook[i].getPieceImage());
        }

        ///Black Rook initialization
        Rook [] Blackrook = new Rook[2];
        ImageView [] BlackRook = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Blackrook[i] = new Rook("images/Black_Rook.png",Piece.Colour.Black);
            BlackRook[i] = new ImageView(Blackrook[i].getPieceImage());
        }

        /// Knight initialization
        Knight [] Whiteknight = new Knight[2];
        ImageView [] WhiteKnight = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Whiteknight[i]= new Knight("images/White_Knight.png",Piece.Colour.White);
            WhiteKnight[i] = new ImageView(Whiteknight[i].getPieceImage());
        }

        /// Black Knight initialization
        Knight [] Blackknight = new Knight[2];
        ImageView [] BlackKnight = new ImageView[2];
        for (int i = 0; i < 2; i++)
        {
            Blackknight[i]=new Knight("images/Black_Knight.png",Piece.Colour.Black);
            BlackKnight[i] = new ImageView(Blackknight[i].getPieceImage());
        }

        ///king initialization
        King Blackking = new King("images/Black_King.png",Piece.Colour.Black);
        ImageView BlackKing = new ImageView(Blackking.getPieceImage());

        King Whiteking = new King("images/White_King.png",Piece.Colour.Black);
        ImageView WhiteKing = new ImageView(Whiteking.getPieceImage());

        ///Board initialization
        for (int row = 0; row < 8; row++) {
            for (int coloumn = 0; coloumn < 8; coloumn++) {
                Pane square = new Pane();
                if ((row + coloumn) % 2 == 0) {
                    square.setStyle("-fx-background-color: #ffffff;");
                } else {
                    square.setStyle("-fx-background-color: #9d501b;");
                }
                Board.add(square, coloumn, row);
                square.setOnMousePressed(event -> {
                    clearMoveIndicators(Board);
                });
            }
        }

        ///Piece adding and setting its position inside the cell
        PieceInitilization(Blackqueen,BlackQueen,3,0);
        PieceInitilization(Whitequeen,WhiteQueen,3,7);
        PieceInitilization(Blackbishop[0],BlackBishop[0],2,0);
        PieceInitilization(Blackbishop[1],BlackBishop[1],5,0);
        PieceInitilization(Whitebishop[0],WhiteBishop[0],2,7);
        PieceInitilization(Whitebishop[1],WhiteBishop[1],5,7);
        PieceInitilization(Whiterook[0],WhiteRook[0],0,7);
        PieceInitilization(Whiterook[1],WhiteRook[1],7,7);
        PieceInitilization(Blackrook[0],BlackRook[0],0,0);
        PieceInitilization(Blackrook[1],BlackRook[1],7,0);
        PieceInitilization(Whiteking,WhiteKing,4,7);
        PieceInitilization(Blackking,BlackKing,4,0);
        PieceInitilization(Whiteknight[0],WhiteKnight[0],1,7);
        PieceInitilization(Whiteknight[1],WhiteKnight[1],6,7);
        PieceInitilization(Blackknight[0],BlackKnight[0],1,0);
        PieceInitilization(Blackknight[1],BlackKnight[1],6,0);
        PawnInitialization(Whitepawns,WhitePawns,0,6);
        PawnInitialization(Blackpawns,BlackPawns,0,1);


    }

    public void PieceInitilization(Piece p, ImageView i,int x,int y){

        i.fitWidthProperty().bind(Board.widthProperty().divide(8));
        i.fitHeightProperty().bind(Board.heightProperty().divide(8));
        i.setPreserveRatio(true);
        GridPane.setHalignment(i, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(i, javafx.geometry.VPos.CENTER);
        Board.add(i, x, y);
        final boolean[] isSelected = {false};



        ///Saving coordinates of the piece
        i.setOnMousePressed(event -> {
            Integer c = GridPane.getColumnIndex(i);
            Integer r = GridPane.getRowIndex(i);

            // Store inside the array index 0
            p.sourceCol[0] = (c == null) ? 0 : c;
            p.sourceRow[0] = (r == null) ? 0 : r;

            p.mouseAnchor[0] = event.getX();
            p.mouseAnchor[1] = event.getY();
            i.toFront();
            clearMoveIndicators(Board);

            if(isSelected[0])
            {
                isSelected[0]=false;
            }
            else{
                ShowAvailableMoves(p,p.sourceCol[0], p.sourceRow[0]);
                isSelected[0] = true;
            }

            isSelected[0] =true;
            /// animation of the piece on being clicked
            ScaleTransition scale = new ScaleTransition(Duration.millis(150), i);
            scale.setToX(1.2);
            scale.setToY(1.2);
            scale.setAutoReverse(true);
            scale.setCycleCount(2);
            scale.play();

        });

        i.setOnMouseDragged(event -> {
            Point2D mouseInBoard = Board.sceneToLocal(event.getSceneX(), event.getSceneY());

            // Calculate current cell size
            double cellWidth = Board.getWidth() / Board.getColumnCount();
            double cellHeight = Board.getHeight() / Board.getRowCount();

            // Convert current mouse pixel location straight to cell indices
            int currentDragCol = (int) (mouseInBoard.getX() / cellWidth);
            int currentDragRow = (int) (mouseInBoard.getY() / cellHeight);

            // Bound safety check (keep inside 0-7 for standard chess)
            currentDragCol = Math.max(0, Math.min(currentDragCol, Board.getColumnCount() - 1));
            currentDragRow = Math.max(0, Math.min(currentDragRow, Board.getRowCount() - 1));

            // Instantly update the image view position to the hovered cell
            GridPane.setColumnIndex(i, currentDragCol);
            GridPane.setRowIndex(i, currentDragRow);

            System.out.println(currentDragRow);
            System.out.println(currentDragCol);
        });

        i.setOnMouseReleased(event -> {
            int targetCol = GridPane.getColumnIndex(i);
            int targetRow = GridPane.getRowIndex(i);
            Integer sRow = p.sourceRow[0];
            Integer sCol = p.sourceCol[0];

            // Run your chess rules using ONLY the cell numbers
            boolean moveAllowed = validateChessMove(p,p.sourceCol[0], p.sourceRow[0], targetCol, targetRow);

            if (moveAllowed) {
                System.out.println("Move committed from cell (" + p.sourceCol.toString() + "," + p.sourceRow.toString() + ") to (" + targetCol + "," + targetRow + ")");

            } else {
                // If illegal, snap it instantly back to the saved source cell coordinates
                GridPane.setColumnIndex(i, sCol);
                GridPane.setRowIndex(i, sRow);
                System.out.println("Illegal move! Snapped back to starting cell.");
            }
        });


    }

    public void PawnInitialization(Piece [] p , ImageView [] i, int x, int y){
        for(int j = 0; j<8;j++) {
            i[j].fitWidthProperty().bind(Board.widthProperty().divide(8));
            i[j].fitHeightProperty().bind(Board.heightProperty().divide(8));
            i[j].setPreserveRatio(true);
            GridPane.setHalignment(i[j], javafx.geometry.HPos.CENTER);
            GridPane.setValignment(i[j], javafx.geometry.VPos.CENTER);
            Board.add(i[j], x, y);
            x++;



            ///Saving coordinates of the piece
            int finalJ = j;
            i[j].setOnMousePressed(event -> {
                Integer c = GridPane.getColumnIndex(i[finalJ]);
                Integer r = GridPane.getRowIndex(i[finalJ]);

                // Store inside the array index 0
                p[finalJ].sourceCol[0] = (c == null) ? 0 : c;
                p[finalJ].sourceRow[0] = (r == null) ? 0 : r;

                p[finalJ].mouseAnchor[0] = event.getX();
                p[finalJ].mouseAnchor[1] = event.getY();
                i[finalJ].toFront();
            });

            i[j].setOnMouseDragged(event -> {
                Point2D mouseInBoard = Board.sceneToLocal(event.getSceneX(), event.getSceneY());

                // Calculate current cell size
                double cellWidth = Board.getWidth() / Board.getColumnCount();
                double cellHeight = Board.getHeight() / Board.getRowCount();

                // Convert current mouse pixel location straight to cell indices
                int currentDragCol = (int) (mouseInBoard.getX() / cellWidth);
                int currentDragRow = (int) (mouseInBoard.getY() / cellHeight);

                // Bound safety check (keep inside 0-7 for standard chess)
                currentDragCol = Math.max(0, Math.min(currentDragCol, Board.getColumnCount() - 1));
                currentDragRow = Math.max(0, Math.min(currentDragRow, Board.getRowCount() - 1));

                // Instantly update the image view position to the hovered cell
                GridPane.setColumnIndex(i[finalJ], currentDragCol);
                GridPane.setRowIndex(i[finalJ], currentDragRow);

                System.out.println(currentDragRow);
                System.out.println(currentDragCol);
            });

            i[j].setOnMouseReleased(event -> {
                int targetCol = GridPane.getColumnIndex(i[finalJ]);
                int targetRow = GridPane.getRowIndex(i[finalJ]);
                Integer sRow = p[finalJ].sourceRow[0];
                Integer sCol = p[finalJ].sourceCol[0];

                // Run your chess rules using ONLY the cell numbers
                boolean moveAllowed = validateChessMove(p[finalJ], p[finalJ].sourceCol[0], p[finalJ].sourceRow[0], targetCol, targetRow);

                if (moveAllowed) {
                    System.out.println("Move committed from cell (" + Arrays.toString(p[finalJ].sourceCol) + "," + Arrays.toString(p[finalJ].sourceRow) + ") to (" + targetCol + "," + targetRow + ")");
                } else {
                    // If illegal, snap it instantly back to the saved source cell coordinates
                    GridPane.setColumnIndex(i[finalJ], sCol);
                    GridPane.setRowIndex(i[finalJ], sRow);
                    System.out.println("Illegal move! Snapped back to starting cell.");
                }
            });

        }
    }

    public boolean validateChessMove(Piece p, int y1, int x1, int y2, int x2) {
        if (p instanceof Queen) {
            if ((((x1 - x2) == (y2 - y1)) || ((x2 - x1) == (y2 - y1)) || ((x1 - x2) == (y1 - y2)) || ((x2 - x1) == (y1 - y2)) || (x2 - x1 == 0) || (y2 - y1 == 0))&& !(x1==x2 && y1==y2)) {
                System.out.println("success");
                return true;
            } else {
                System.out.println("failure");
                return false;
            }
        }
        if (p instanceof Bishop) {
            if ((((x1 - x2) == (y2 - y1)) || ((x2 - x1) == (y2 - y1)) || ((x1 - x2) == (y1 - y2)) || ((x2 - x1) == (y1 - y2)))&& !(x1==x2 && y1==y2)) {
                System.out.println("success");
                return true;
            }
             else {
                System.out.println("failure");
                return false;
            }
        }

        if (p instanceof Rook) {
            if ( ((x2-x1==0)||(y2-y1==0))&& !(x1==x2 && y1==y2)) {
                System.out.println("success");
                return true;
            } else {
                System.out.println("failure");
                return false;
            }
        }

        if (p instanceof Knight) {
            if(abs(x2-x1)==2 && abs(y2-y1)==1 || abs(x2-x1)==1 && abs(y2-y1)==2)
            {
                System.out.println("success");
                return true;
            }
            else{
                System.out.println("failure");
                return false;
            }

        }
        if(p instanceof King) {
            if(abs(x2-x1)<=1 && abs(y2-y1)<=1)
            {
                System.out.println("success");
                return true;
            }
            else{
                System.out.println("failure");
                return false;
            }
        }
        if(p instanceof BlackPawn)
        {
            if ( ( (x2-x1==1) || (( (BlackPawn) p).FirstMove&& (x2-x1==2)))&& !(x1==x2 && y1==y2))
            {
                System.out.println("success");
                ((BlackPawn) p).FirstMove=false;
                return true;
            }
            else{
                System.out.println("failure");
                return false;
            }
        }
        if(p instanceof WhitePawn)
        {
            if ( ( (x1-x2==1) || (( (WhitePawn) p).FirstMove&& (x1-x2==2)))&& !(x1==x2 && y1==y2))
            {
                System.out.println("success");
                ((WhitePawn)p).FirstMove=false;
                return true;
            }
            else{
                System.out.println("failure");
                return false;
            }
        }

        return false;
    }

    public void ShowAvailableMoves(Piece p,int x,int y) {
        Circle [] dots= new Circle[64];
        for(int i=0;i<64;i++)
        {
            dots[i] = new Circle(20,Color.BLACK);
            dots[i].setOpacity(0.3);
        }
        int k =0;

        /// replace x1 with x, y1 with y, x2 with i, and y2 with j

        for (int i = 0; i < Board.getRowCount(); i++) {
            for (int j = 0; j < Board.getColumnCount(); j++) {
                if (p instanceof Queen) {
                    if((((x - i) == (j - y)) || ((i - x) == (j - y)) || ((x - i) == (y - j)) || ((i - x) == (y - j)) || (i - x == 0) || (j - y == 0))&&!(x==i && y==j))
                    {
                        dots[k].setId("move-indicator");
                        GridPane.setHalignment(dots[k], javafx.geometry.HPos.CENTER);
                        GridPane.setValignment(dots[k], javafx.geometry.VPos.CENTER);
                        Board.add(dots[k], i, j);
                        k++;
                    }
                    else {
                        System.out.println("cannot place in ("+i+","+j+")");
                    }

                }
                if (p instanceof Bishop) {
                    if ((((x - i) == (j - y)) || ((i - x) == (j - y)) || ((x - i) == (y - j)) &&!(x==i && y==j)))
                    {
                        dots[k].setId("move-indicator");
                        GridPane.setHalignment(dots[k], javafx.geometry.HPos.CENTER);
                        GridPane.setValignment(dots[k], javafx.geometry.VPos.CENTER);
                        Board.add(dots[k], i, j);
                        k++;
                    }
                    else {
                        System.out.println("cannot place in ("+i+","+j+")");
                    }
                }
                if (p instanceof Rook) {
                    if((( (i - x == 0) || (j - y == 0))&&!(x==i && y==j)))
                    {
                        dots[k].setId("move-indicator");
                        GridPane.setHalignment(dots[k], javafx.geometry.HPos.CENTER);
                        GridPane.setValignment(dots[k], javafx.geometry.VPos.CENTER);
                        Board.add(dots[k], i, j);
                        k++;
                    }

                }
                if (p instanceof Knight) {
                    if(abs(i-x)==2 && abs(j-y)==1 || abs(i-x)==1 && abs(j-y)==2)
                    {
                        dots[k].setId("move-indicator");
                        GridPane.setHalignment(dots[k], javafx.geometry.HPos.CENTER);
                        GridPane.setValignment(dots[k], javafx.geometry.VPos.CENTER);
                        Board.add(dots[k], i, j);
                        k++;
                    }
                }
            }
        }

    }

    /// Under studying

    public void clearMoveIndicators(GridPane grid) {
        // Collect dots in a separate list first to avoid crash bugs while looping
        List<Node> indicatorsToRemove = new ArrayList<>();

        for (Node node : grid.getChildren()) {
            if (node instanceof Circle && "move-indicator".equals(node.getId())) {
                indicatorsToRemove.add(node);
            }
        }

        // Remove them completely from the grid layout
        grid.getChildren().removeAll(indicatorsToRemove);
    }










    private void removePieceAtCell(GridPane board, int col, int row, ImageView activePiece) {
        // Use an Iterator or standard loop over a copy to avoid ConcurrentModificationException
        java.util.List<javafx.scene.Node> children = new java.util.ArrayList<>(board.getChildren());

        for (javafx.scene.Node node : children) {
            // Only look at images, and ignore the piece we are currently dragging
            if (node instanceof ImageView && node != activePiece) {
                Integer nodeCol = GridPane.getColumnIndex(node);
                Integer nodeRow = GridPane.getRowIndex(node);

                // Fallback defaults to 0 if not explicitly defined
                int c = (nodeCol == null) ? 0 : nodeCol;
                int r = (nodeRow == null) ? 0 : nodeRow;

                // If coordinates match, this piece is captured! Remove it.
                if (c == col && r == row) {
                    board.getChildren().remove(node);
                    System.out.println("Captured a piece at cell (" + col + "," + row + ")!");
                    break; // Stop looking once we found and removed the piece
                }
            }
        }
    }
}
