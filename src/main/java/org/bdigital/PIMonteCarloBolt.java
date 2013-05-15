package org.bdigital;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class PIMonteCarloBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 1L;

    public void execute(Tuple input, BasicOutputCollector collector) {
	
	int iter_num = Integer.parseInt(input.getString(1));
	double pi = PIMonteCarlo.calcularPI(iter_num);
	collector.emit(new Values(input.getValue(0), pi));
	
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
	declarer.declare(new Fields("id", "pi"));
    }

}
