package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class DirectionTools {
    public static Vector2 directionToVector(Direction dir){
        if (dir==Direction.NORTH){
            return new Vector2(0,1);
        }
        else if (dir==Direction.WEST){
            return new Vector2(-1,0);
        }
        else if (dir==Direction.SOUTH){
            return new Vector2(0,-1);
        }
        else if (dir==Direction.EAST){
            return new Vector2(1,0);
        }
        return null;
    }

    public static Direction rotationToDirection(Direction playerDir, boolean clockwise){
        Direction[] directionList = {Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST};
        int dirIndex=directionList.length;
        for(int i = 0; i<directionList.length; i++){
            if(directionList[i]==playerDir){
                dirIndex = i;
                i+=directionList.length;
            }
        }
        dirIndex += clockwise ? 1 : -1;
        if(dirIndex<0)
            dirIndex = directionList.length-1;
        if(dirIndex>=directionList.length)
            dirIndex = 0;
        return directionList[dirIndex];
    }
}
