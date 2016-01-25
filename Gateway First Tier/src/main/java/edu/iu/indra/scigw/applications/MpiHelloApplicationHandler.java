package edu.iu.indra.scigw.applications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import edu.iu.indra.scigw.util.PbsScriptUtil;

public class MpiHelloApplicationHandler extends ApplicationHandler
{
	@Override
	protected void getInputForJob()
	{
		//no input required for mpi hello as of now
		/*// get path of file having comma separated numbers
		System.out.println("Please input message file path for MPI hello app");
		String filePath = scanner.nextLine();
		UUID jobId = jobConfig.getUid();
		String jobDirPath = constants.getJobDirPath(jobId.toString());
		jobConfig.addInputFile(filePath, jobDirPath + "input.txt");*/
	}

	@Override
	protected String generatePbsScriptFile()
	{
		StringBuilder script = PbsScriptUtil.createPbsScript(jobConfig);

		// append exe file path to script
		String exeCommand = constants.getMpiHelloRunCommand(jobConfig);

		script.append(exeCommand);

		// write script to file
		File bashScript = new File("mpi.sh");

		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(bashScript));
			writer.write(script.toString());
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return bashScript.getAbsolutePath();
	}

}
