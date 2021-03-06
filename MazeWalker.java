import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class MazeWalker extends Actor
{
    private final int NORTH = 270;
    private final int EAST = 0;
    private final int SOUTH = 90;
    private final int WEST = 180;

    public MazeWalker()
    {
        GreenfootImage playerImage = new GreenfootImage(40, 40);

        playerImage.setColor(Color.GREEN);
        playerImage.fillRect(0, 0, 40, 40);

        setImage(playerImage);
        setRotation(WEST);
    }

    /**
     * checkWin will check if player has reached WinningSpace and show a message to say maze is finished
     * The game stops
     * @param There are no parameters
     * @return nothing is returned
     */
    private void checkWin()
    {
        if( isTouching(WinningSpace.class) )
        {
            getWorld().showText("You are A Loser", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }

    /**
     * wallOnLeft checks whether there is a wall on the players left
     * @param There are no parameters
     * @return a boolean stating whether or not there is a wall on the left
     */
    private boolean wallOnLeft()
    {
        int xOffset = 0;
        int yOffset= 0;

        boolean wallLeft = false;
        if (getRotation() == NORTH)
        {
            xOffset = -1;
        }
        else if (getRotation() == SOUTH)
        {
            xOffset = 1;
        }
        else if (getRotation() == EAST)
        {
            yOffset = -1;
        }
        else if (getRotation() == WEST)
        {
            yOffset = 1;
        }

        if( getOneObjectAtOffset(xOffset, yOffset, Wall.class ) != null )
        {
            wallLeft = true;
        }

        return wallLeft;
    }

    /**
     * CanMoveForward checks whether the player can move forward
     * @param There are no parameters
     * @return a boolean stating whether we can move forward or not
     */
    private boolean canMoveForward()
    {
        int xOffset = 0;
        int yOffset= 0;
        boolean moveForward = false;

        if (getRotation() == NORTH)
        {
            yOffset = -1;
        }
        else if (getRotation() == SOUTH)
        {
            yOffset = 1;
        }
        else if (getRotation() == EAST)
        {
            xOffset = 1;
        }
        else if (getRotation() == WEST)
        {
            xOffset = -1;
        }

        if( getOneObjectAtOffset(xOffset, yOffset, Wall.class ) == null )
        {
            moveForward = true;
        }

        return moveForward;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkWin();

        if( wallOnLeft() == true)
        {
            if ( canMoveForward() == true)
            {
                move(1);
            }
            else
            {
                setRotation(getRotation() + 90);
            }
        }
        else
        {
            setRotation(getRotation() - 90);
            if( canMoveForward() == true )
            {
                move(1);
            }
        }

    }    
}
