package org.bdigital;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.drpc.LinearDRPCTopologyBuilder;
import backtype.storm.utils.DRPCClient;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

	LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder("piMontecarlo");
	builder.addBolt(new PIMonteCarloBolt(), 1);
	
	LocalDRPC drpcServer = new LocalDRPC();
	LocalCluster cluster = new LocalCluster();
	Config conf = new Config();
	cluster.submitTopology("calculadoraPI", conf, builder.createLocalTopology(drpcServer));
	
	// Client en producció
	//DRPCClient client = new DRPCClient("10.0.0.1", 2288);
	//String result = client.execute("piMontecarlo", "100000");
	
	double result = Double.parseDouble(drpcServer.execute("piMontecarlo", "1000000"));
	System.out.println(result);
	
    }

}
