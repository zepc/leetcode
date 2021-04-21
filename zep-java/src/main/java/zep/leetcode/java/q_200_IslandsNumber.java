package zep.leetcode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/08/02.
 *
 * @author Zhao Peng
 */
public class q_200_IslandsNumber {

	public static int numIslands(char[][] grid) {
		return solution_03(grid);
	}

	public static int solution_01(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int num_island = 0;

		Queue<Integer[]> neighbors = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();

		int nr = grid.length;
		int nc = grid[0].length;

		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				String s = r + "-" + c;
				if (grid[r][c] == '1' && !visited.contains(s)) {
					Integer[] num = new Integer[2];
					num[0] = r;
					num[1] = c;
					neighbors.offer(num);
					visited.add(s);

					++num_island;

					while (!neighbors.isEmpty()) {
						Integer[] id = neighbors.poll();
						int tr = id[0];
						int tc = id[1];

						// 上
						if (tr - 1 >= 0 && grid[tr - 1][tc] == '1' && !visited.contains(((tr - 1) + "-" + tc))) {
							Integer[] up = new Integer[2];
							up[0] = tr - 1;
							up[1] = tc;
							neighbors.offer(up);
							visited.add(up[0] + "-" + up[1]);
						}
						// 下
						if (tr + 1 < grid.length && grid[tr + 1][tc] == '1' && !visited.contains(((tr + 1) + "-" + tc))) {
							Integer[] down = new Integer[2];
							down[0] = tr + 1;
							down[1] = tc;
							neighbors.offer(down);
							visited.add(down[0] + "-" + down[1]);
						}
						// 左
						if (tc - 1 >= 0 && grid[tr][tc - 1] == '1' && !visited.contains((tr + "-" + (tc - 1)))) {
							Integer[] left = new Integer[2];
							left[0] = tr;
							left[1] = tc - 1;
							neighbors.offer(left);
							visited.add(left[0] + "-" + left[1]);
						}
						// 右
						if (tc + 1 < grid[0].length && grid[tr][tc + 1] == '1' && !visited.contains((tr + "-" + (tc + 1)))) {
							Integer[] right = new Integer[2];
							right[0] = tr;
							right[1] = tc + 1;
							neighbors.offer(right);
							visited.add(right[0] + "-" + right[1]);
						}
					}
				}
			}
		}
		return num_island;
	}

	// 通过BFS进行查找
	public static int solution_02(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;

		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				if (grid[r][c] == '1') {
					++num_islands;
					grid[r][c] = '0'; // ##将’0‘设置为’1‘，这样就可以过滤掉访问过的坐标
					Queue<Integer> neighbors = new LinkedList<>();
					neighbors.add(r * nc + c);  // ##通过 r * max(c) + c 来生成id， 用来记录r和c
					while (!neighbors.isEmpty()) {
						Integer id = neighbors.poll();
						int tr = id / nc;
						int tc = id % nc;
						// 上
						if (tr - 1 >= 0 && grid[tr - 1][tc] == '1') {
							neighbors.offer((tr - 1) * nc + tc);
							grid[tr - 1][tc] = '0';
						}
						// 下
						if (tr + 1 < grid.length && grid[tr + 1][tc] == '1') {
							neighbors.offer((tr + 1) * nc + tc);
							grid[tr + 1][tc] = '0';
						}
						// 左
						if (tc - 1 >= 0 && grid[tr][tc - 1] == '1') {
							neighbors.offer(tr * nc + tc - 1);
							grid[tr][tc - 1] = '0';
						}
						// 右
						if (tc + 1 < grid[0].length && grid[tr][tc + 1] == '1') {
							neighbors.offer(tr * nc + tc + 1);
							grid[tr][tc + 1] = '0';
						}
					}
				}
			}
		}
		return num_islands;
	}

	// 通过DFS进行查找
	// 每次搜索到1变为0，搜索的次数就是岛屿的数量
	public static int solution_03(char[][] grid) {
		int numIslands = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					++numIslands;
					dfs(grid, i, j);
				}
			}
		}
		return numIslands;
	}

	/**
	 * m1 - 使用DFS进行搜索 每搜索到一个1，标记为0，继续搜索， 搜索的次数就是岛屿的数量
	 *
	 * @param grid
	 * @return
	 */
	private static void dfs(char[][] grid, int r, int c) {
		int nr = grid.length;
		int nc = grid[0].length;

		if (r >= nr || c >= nc || r < 0 || c < 0 || (grid[r][c] == '0')) {
			return;
		}

		grid[r][c] = '0';
		dfs(grid, r - 1, c); // 上
		dfs(grid, r + 1, c); // 下
		dfs(grid, r, c - 1); // 左
		dfs(grid, r, c + 1); // 右
	}


	public static int solution_04(char[][] grid) {

		// 使用并查集
		class UnionFind {

			int count;
			final int[] parent;
			final int[] rank;

			public UnionFind(char[][] grid) {
				count = 0;
				int nr = grid.length;
				int nc = grid[0].length;
				parent = new int[nr * nc];
				rank = new int[nr * nc];
				for (int i = 0; i < nr; ++i) {
					for (int j = 0; j < nc; ++j) {
						if (grid[i][j] == '1') {
							parent[i * nc + j] = i * nc + j;
							++count;
						}
						rank[i * nc + j] = 0;
					}
				}
			}

			public int find(int i) {
				if (parent[i] != i) {
					parent[i] = find(parent[i]);
				}
				return parent[i];
			}

			public void union(int x, int y) {
				int rootx = find(x);
				int rooty = find(y);
				if (rootx != rooty) {
					if (rank[rootx] > rank[rooty]) {
						parent[rooty] = rootx;
					}
					else if (rank[rootx] < rank[rooty]) {
						parent[rootx] = rooty;
					}
					else {
						parent[rooty] = rootx;
						rank[rootx] += 1;
					}
					--count;
				}
			}

			public int getCount() {
				return count;
			}
		}

		UnionFind ufind = new UnionFind(grid);
		return ufind.count;
	}


	public static void main(String[] args) {
		char[][] g1 = {
				{'1', '1', '1', '1', '0'},
				{'1', '1', '0', '1', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '0', '0', '0'}
		};

		char[][] g2 = {
				{'1', '1', '0', '0', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '1', '0', '0'},
				{'0', '0', '0', '1', '1'}
		};

		char[][] g3 = {
				{'1', '1', '1'},
				{'0', '1', '0'},
				{'1', '1', '1'}
		};
		System.out.println(g2.length);
		System.out.println(numIslands(g3));
	}
}
