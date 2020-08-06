package com.dripping.service.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 描述：
 *
 * @Author: mabiao
 * @Date: 2020/8/6 14:40
 * @Version 1.0
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] words = value.toString().split("\\s+");

        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
