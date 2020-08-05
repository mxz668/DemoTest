package com.dripping.service.logClean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

public class LogCleanJob{

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "F:\\Software\\hd\\hadoop-2.8.5");
        Configuration conf = new Configuration();
        args = new String[]{"F:\\datas\\zin","F:\\datas\\zout"};

        try {
            // 1 获取job信息
            Job job = Job.getInstance(conf);
            // 2 加载jar包
            job.setJarByClass(LogCleanJob.class);
            // 3 关联map
            job.setMapperClass(MyMapper.class);
            // 4 设置最终输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);
            // 5 设置输入和输出路径
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            // 6 提交
            job.waitForCompletion(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    @Override
//    public int run(String[] args) throws Exception {
//        final Job job = new Job(new Configuration(),
//                LogCleanJob.class.getSimpleName());
//        // 设置为可以打包运行
//        job.setJarByClass(LogCleanJob.class);
//        FileInputFormat.setInputPaths(job, args[0]);
//        job.setMapperClass(MyMapper.class);
//        job.setMapOutputKeyClass(LongWritable.class);
//        job.setMapOutputValueClass(Text.class);
//        job.setReducerClass(MyReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(NullWritable.class);
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        // 清理已存在的输出文件
////        FileSystem fs = FileSystem.get(new URI(args[0]), getConf());
////        Path outPath = new Path(args[1]);
////        if (fs.exists(outPath)) {
////            fs.delete(outPath, true);
////        }
//
//        boolean success = job.waitForCompletion(true);
//        if (success) {
//            System.out.println("Clean process success!");
//        } else {
//            System.out.println("Clean process failed!");
//        }
//        return 0;
//    }
}
