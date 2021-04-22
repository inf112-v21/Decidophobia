package inf112.skeleton.app.gameboard;

import inf112.skeleton.app.Direction;
import java.util.HashMap;

public enum Tiles {

    EAST_WALL(23),
    SOUTH_WALL(29),
    WEST_WALL(30),
    NORTH_WALL(31),

    EAST_LASER_WALL(46),
    SOUTH_LASER_WALL(37),
    WEST_LASER_WALL(38),
    NORTH_LASER_WALL(45),

    SOUTHEAST_WALL(8),
    NORTHEAST_WALL(16),
    NORTHWEST_WALL(24),
    SOUTHWEST_WALL(32),

    VERTICAL_LASER(47),
    HORIZONTAL_LASER(39),
    CROSSED_LASER(40),

    NORMAL_HOLE(6),
    NORMAL_HOLE2(91),
    NORTHWEST_HOLE(105),
    NORTH_HOLE(106),
    NORTHEAST_HOLE(107),
    EAST_HOLE(108),
    NORTH_EAST_SOUTH_HOLE(109),
    WEST_EAST_SOUTH_HOLE(110),
    SOUTHWEST_HOLE(113),
    SOUTH_HOLE(114),
    SOUTHEAST_HOLE(115),
    WEST_HOLE(116),
    NORTH_WEST_SOUTH_HOLE(117),
    NORTH_WEST_EAST_HOLE(118),

    ROTATE_PAD_LEFT(53),
    ROTATE_PAD_RIGHT(54),

    WRENCH(15),
    DOUBLE_WRENCH(7),

    FLAG_1(55),
    FLAG_2(63),
    FLAG_3(71),
    FLAG_4(79),

    EAST_TO_SOUTH_BELT(33),
    NORTH_TO_EAST_BELT(41),
    WEST_TO_NORTH_BELT(42),
    SOUTH_TO_WEST_BELT(34),

    EAST_TO_NORTH_BELT(43),
    NORTH_TO_WEST_BELT(44),
    WEST_TO_SOUTH_BELT(36),
    SOUTH_TO_EAST_BELT(35),

    EAST_TO_WEST_BELT(51),
    NORTH_TO_SOUTH_BELT(50),
    WEST_TO_EAST_BELT(52),
    SOUTH_TO_NORTH_BELT(49),

    WESTSOUTH_TO_NORTH_BELT(57),
    EASTSOUTH_TO_NORTH_BELT(65),
    WESTEAST_TO_NORTH_BELT(69),

    WESTNORTH_TO_SOUTH_BELT(67),
    EASTNORTH_TO_SOUTH_BELT(59),
    WESTEAST_TO_SOUTH_BELT(62),

    WESTSOUTH_TO_EAST_BELT(66),
    WESTNORTH_TO_EAST_BELT(58),
    NORTHSOUTH_TO_EAST_BELT(61),

    EASTSOUTH_TO_WEST_BELT(60),
    EASTNORTH_TO_WEST_BELT(68),
    NORTHSOUTH_TO_WEST_BELT(70),

    EAST_TO_SOUTH_EXPRESS_BELT(17),
    NORTH_TO_EAST_EXPRESS_BELT(25),
    WEST_TO_NORTH_EXPRESS_BELT(26),
    SOUTH_TO_WEST_EXPRESS_BELT(18),

    EAST_TO_NORTH_EXPRESS_BELT(27),
    NORTH_TO_WEST_EXPRESS_BELT(28),
    WEST_TO_SOUTH_EXPRESS_BELT(20),
    SOUTH_TO_EAST_EXPRESS_BELT(19),

    EAST_TO_WEST_EXPRESS_BELT(22),
    NORTH_TO_SOUTH_EXPRESS_BELT(21),
    WEST_TO_EAST_EXPRESS_BELT(14),
    SOUTH_TO_NORTH_EXPRESS_BELT(13),

    WESTSOUTH_TO_NORTH_EXPRESS_BELT(73),
    EASTSOUTH_TO_NORTH_EXPRESS_BELT(77),
    WESTEAST_TO_NORTH_EXPRESS_BELT(84),

    WESTNORTH_TO_SOUTH_EXPRESS_BELT(86),
    EASTNORTH_TO_SOUTH_EXPRESS_BELT(75),
    WESTEAST_TO_SOUTH_EXPRESS_BELT(82),

    WESTSOUTH_TO_EAST_EXPRESS_BELT(78),
    WESTNORTH_TO_EAST_EXPRESS_BELT(74),
    NORTHSOUTH_TO_EAST_EXPRESS_BELT(81),

    EASTSOUTH_TO_WEST_EXPRESS_BELT(76),
    EASTNORTH_TO_WEST_EXPRESS_BELT(85),
    NORTHSOUTH_TO_WEST_EXPRESS_BELT(83),

    START_POS1(121),
    START_POS2(122),
    START_POS3(123),
    START_POS4(124),
    START_POS5(129),
    START_POS6(130),
    START_POS7(131),
    START_POS8(132);

    private final int id;


    Tiles(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HashMap<Direction, Integer> getPlayerID(String colour) {
        HashMap<Direction, Integer> IDs = new HashMap<>();
        for (int i = 0; i < 36; i++) {
            if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_SOUTH")) {
                IDs.put(Direction.SOUTH, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_NORTH")) {
                IDs.put(Direction.NORTH, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ","") + "_WEST")) {
                IDs.put(Direction.WEST, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_EAST")) {
                IDs.put(Direction.EAST, values() [i].getId());
            }
        }
        return IDs;
    }
}
