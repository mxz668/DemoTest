package com.dripping.service.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RunJob {
    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        FileSystem fs = FileSystem.get(configuration);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(RunJob.class);
        job.setJobName("wordCount");

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("F:/datas/zin"));
        Path outPath = new Path("F:/datas/zout");
        if (fs.exists(outPath)) {
            fs.delete(outPath, true);
        }
        FileOutputFormat.setOutputPath(job, outPath);

        boolean completion = job.waitForCompletion(true);
        if (completion) {
            System.out.println("执行完成");
        }

    }
}
