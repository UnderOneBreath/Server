public class Field {
    private final int FREE = 0;
    private final int WALL = 1;
    private final int UNIT = 2;
    private final int FLOWER = 3;
    private int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
    };

    public int[][] getMap() {
        return map;
    }
    public int[][] getCloneMap(){
        int[][] cloneMap = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            int[] temp = new int[map[i].length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = -1*map[i][j]-1;
            }
            cloneMap[i] = temp;
        }
        return cloneMap;
    }

    public void putItem(int x, int y) {
        if(map[y][x] == FREE)
            map[y][x] = UNIT;
    }

    public void remove(int x, int y) {
        map[y][x] = FREE;
    }

    public void build(int x, int y) {
        if(map[y][x] == FREE) {
            map[y][x] = WALL;
        }
    }

    public void destroy(int x, int y) {
        map[y][x] = FREE;
    }

    public boolean isFree(int x, int y) {
        return map[y][x] == FREE;
    }

    public boolean isUnit(int x, int y){
        return map[y][x] == UNIT;
    }

    public void buildFlower(int x, int y) {
        if(map[y][x] == FREE) {
            map[y][x] = FLOWER;
        }
    }
}
