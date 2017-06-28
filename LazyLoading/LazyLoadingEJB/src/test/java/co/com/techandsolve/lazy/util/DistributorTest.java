package co.com.techandsolve.lazy.util;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import co.com.techandsolve.lazy.dto.Tasks;
import co.com.techandsolve.lazy.exception.BusinessException;

public class DistributorTest {
	
	@Test(expected=BusinessException.class)
	public void debeLanzarExpecionListaNulla(){
		Distributor.run(null, 50);
	}
	
	@Test(expected=BusinessException.class)
	public void debeLanzarExpecionPesoCero(){
		Distributor.run(getTaskEmpty(), 0);
	}
	
	@Test
	public void debeRetornarUnViaje(){
		int viajes = Distributor.run(getTask(1), 30);
		Assert.assertTrue(viajes == 1);
	}

	private Tasks getTask(int size) {
		Tasks task = getTaskEmpty();
		task.getWeights().add(35);
		return task;
	}

	private Tasks getTaskEmpty() {
		return new Tasks(new ArrayList<Integer>());
	}

}
