package ir.digixo.writer;

import ir.digixo.model.*;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Product> {

    @Override
    public void write(Chunk<? extends Product> list) throws Exception {
        System.out.println("Inside item writer, Items : ");
        list.getItems().stream().forEach(System.out::println);
    }
}
