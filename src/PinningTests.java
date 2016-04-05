import static org.junit.Assert.*;

import org.junit.Test;

public class PinningTests {
	/**
	 * Simply test to see if getNumNeighbors returns the number of neighbors.
	 */
	@Test
	public void mainPanel_getNumNeighbors_testBasic() {
		MainPanel panel = new MainPanel(15);
		panel.getCells()[0][1].setAlive(true);
		panel.getCells()[1][1].setAlive(true);
		panel.getCells()[2][1].setAlive(true);
		int num = panel.getNumNeighbors(1, 1);
		assertEquals(num, 2);
	}
	
	/**
	 * Test whether or not the cells on the edge count cell on opposite edge as neighbors.
	 * They are functioning correctly if they do this.
	 */
	@Test
	public void mainPanel_getNumNeighbors_testEdges() {
		MainPanel panel = new MainPanel(15);
		panel.getCells()[0][0].setAlive(true);
		panel.getCells()[0][14].setAlive(true);
		panel.getCells()[14][0].setAlive(true);
		panel.getCells()[14][14].setAlive(true);
		int num = panel.getNumNeighbors(0, 0);
		assertEquals(num, 3);
	}
	
	/**
	 * Test to see if getNumNeighbors works properly after running.
	 */
	@Test
	public void mainPanel_getNumNeighbors_testAfterRuns() {
		MainPanel panel = new MainPanel(15);
		panel.getCells()[0][1].setAlive(true);
		panel.getCells()[1][1].setAlive(true);
		panel.getCells()[2][1].setAlive(true);
		panel.run();
		panel.run();
		int num = panel.getNumNeighbors(1, 1);
		assertEquals(num, 2);
	}
	
	/**
	 * Test if a cell gets backed up.
	 */
	@Test
	public void mainPanel_backup_testBasic() {
		MainPanel panel = new MainPanel(15);
		panel.backup();
		panel.getCells()[0][0].setAlive(true);
		panel.undo();
		assertFalse(panel.getCells()[0][0].getAlive());
	}
	
	/**
	 * Test to see if a cell is still backed up after a run.
	 */
	@Test
	public void mainPanel_backup_testWorksAfterRun() {
		MainPanel panel = new MainPanel(15);
		panel.getCells()[0][1].setAlive(true);
		panel.getCells()[1][1].setAlive(true);
		panel.getCells()[2][1].setAlive(true);
		panel.run();
		panel.backup();
		panel.run();
		panel.undo();
		assertTrue(panel.getCells()[1][0].getAlive());
	}
	
	/**
	 * Test to see if all of the cells are backed up or not.
	 */
	@Test
	public void mainPanel_backup_testBacksUpAll() {
		MainPanel panel = new MainPanel(15);
		panel.getCells()[0][0].setAlive(true);
		panel.getCells()[0][14].setAlive(true);
		panel.getCells()[14][0].setAlive(true);
		panel.getCells()[14][14].setAlive(true);
		panel.backup();
		panel.clear();
		panel.undo();
		if (!panel.getCells()[0][0].getAlive()) fail();
		else if (!panel.getCells()[0][14].getAlive()) fail();
		else if (!panel.getCells()[14][0].getAlive()) fail();
		else if (!panel.getCells()[14][14].getAlive()) fail();
		assertTrue(true);
	}
	
	/**
	 * Test to see if to string returns the proper value for being alive.
	 */
	@Test
	public void cell_toString_testAlive() {
		Cell cell = new Cell(true);
		assertEquals(cell.toString(), "X");
	}
	
	/**
	 * Test to see if to string returns the proper value for not being alive.
	 */
	@Test
	public void cell_toString_testNotAlive() {
		Cell cell = new Cell(false);
		assertEquals(cell.toString(), ".");
	}
	
	/**
	 * Test to see if getText and toString have different values.
	 * They are functioning correctly if they do this.
	 */
	@Test
	public void cell_toString_testGetTextAndToStringAreDifferent() {
		Cell cell = new Cell(false);
		if (cell.getText() != " ") fail();
		else if (cell.toString() != ".") fail();
		assertTrue(true);
	}
}
