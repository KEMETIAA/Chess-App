package Backend;

public class BlackPawn extends Piece {
    public boolean FirstMove;
    public BlackPawn(String i, Piece.Colour c){
        super(i,c);
        FirstMove=true;
    }
}
