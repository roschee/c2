package ch.rmuerner.c2.db;

import junit.framework.TestCase;

import org.junit.Test;

public class DBAdapterTest extends TestCase{

	@Test
	public void testDBInit(){
		DBAdapter.getInstance().initDB();
		
		
	}
}
