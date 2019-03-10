package com.intuit.gbtc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A simple implementation of Path calculation strategy using Lee's Algorithm.
 * 
 * @author uvalsala
 *
 */
public class SimplePathCalculationStrategy implements PathCalculationStrategy {

	@Override
	public List<Point> getPath(int[][] grid, Point src, Point dest) {
		int maxRow = grid.length;
		int maxCol = grid[0].length;
		boolean visited[][] = new boolean[maxRow][maxCol];

		// mark the starting point as visited.
		visited[src.getX()][src.getY()] = true;

		// initialize with source cell distance as zero
		Queue<QueueNode> q = new ArrayDeque<QueueNode>();
		Map<Point, Point> previousNodes = new HashMap<>();

		QueueNode s = new QueueNode(src, 0);
		q.add(s);

		while (!q.isEmpty()) {
			QueueNode curr = q.poll();
			Point pt = curr.pt;

			// If we are able to reach the destination cell, then a safe path exists.
			// return the distance.
			if (pt.getX() == dest.getX() && pt.getY() == dest.getY()) {

				Point p = dest;
				List<Point> route = new ArrayList<>();
				route.add(dest);
				while (previousNodes.get(p) != null) {
					p = previousNodes.get(p);
					route.add(p);
				}

				// return curr.dist;
				Collections.reverse(route);
				return route;
			}

			for (int i = 0; i < 4; i++) {
				int row = pt.getX() + rowNum[i];
				int col = pt.getY() + colNum[i];

				// if adjacent cell is valid, has path and
				// not visited yet, enqueue it.
				if ((isValid(maxRow, maxCol, row, col) && grid[row][col] == 0) && !visited[row][col]) {
					// mark cell as visited and enqueue it
					visited[row][col] = true;
					Point p = new Point(row, col);
					QueueNode adjCell = new QueueNode(new Point(row, col), curr.dist + 1);
					q.add(adjCell);
					previousNodes.put(p, pt);
				}
			}
		}

		return null;
	}

	boolean isValid(int maxRow, int maxCol, int row, int col) {
		return (((row >= 0) && (row < maxRow)) && ((col >= 0) && (col < maxCol)));
	}

	static class QueueNode {
		public QueueNode(Point src, int d) {
			this.pt = src;
			this.dist = d;
		}

		Point pt;
		int dist;
	}

	private static final int[] rowNum = new int[] { -1, 0, 0, 1 };
	private static final int[] colNum = new int[] { 0, -1, 1, 0 };
}
