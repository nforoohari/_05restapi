package ir.digixo.config;

import ir.digixo.model.*;

import ir.digixo.service.ProductService;
import ir.digixo.writer.FirstItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.context.annotation.*;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class SampleJob {

    final private static int BATCH_SIZE = 3;
    final private JobRepository jobRepository;
    final private PlatformTransactionManager batchTransactionManager;
    final private ProductService productService;
    final private FirstItemWriter firstItemWriter;

    @Bean
    public Job firstJob() {
        return new JobBuilder("First Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }

    @Bean
    public Step firstChunkStep() {
        return new StepBuilder("First Chunk Step", jobRepository)
                .<Product, Product>chunk(BATCH_SIZE, batchTransactionManager)
                .reader(itemReaderAdapter())
                .writer(firstItemWriter)
                .build();
    }

    @Bean
    public ItemReaderAdapter<Product> itemReaderAdapter() {
        ItemReaderAdapter<Product> itemReaderAdapter = new ItemReaderAdapter<Product>();
        itemReaderAdapter.setTargetObject(productService);
        itemReaderAdapter.setTargetMethod("getProduct");
        return itemReaderAdapter;
    }

}
