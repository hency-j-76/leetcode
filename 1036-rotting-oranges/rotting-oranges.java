class Solution {
    class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] v = new int[n][m];
        int fresh = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) {
                    q.offer(new Pair(i, j));
                    v[i][j] = 1;
                }
                if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if(fresh == 0) return 0;
        int count = 0;

        while(!q.isEmpty()) {
            int ele = q.size();
            int[] drow = {-1, 0, 1, 0};
            int[] dcol = {0, 1, 0, -1};
            for(int i = 0; i < ele; i++) {
                Pair node = q.poll();
                int r = node.row;
                int c = node.col;

                for(int k = 0; k < 4; k++) {
                    int nrow = r + drow[k];
                    int ncol = c + dcol[k];

                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == 1 && v[nrow][ncol] == 0) {
                        v[nrow][ncol] = 1;
                        q.offer(new Pair(nrow, ncol));
                        fresh--;
                    }
                }
            }
            count++;
        }
        return (fresh == 0) ? count - 1 : -1;
    }
}