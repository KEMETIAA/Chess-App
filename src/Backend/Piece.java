package Backend;


import javafx.scene.image.Image;

public abstract class Piece {
    String PieceImage;
    public final int[] sourceCol = new int[1];
    public int[] sourceRow = new int[1];
    public final double[] mouseAnchor = new double[2];

    public Image getPieceImage() {
        return new Image(PieceImage);
    }

    public enum Colour { Black, White};
    public Colour colour;
    boolean isLegalmove;

    public Piece(String i, Colour c){
        this.PieceImage=i;
        this.colour=c;
    }
}
