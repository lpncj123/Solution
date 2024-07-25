package cn.lp.easyexecel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.easyexecel
 * @Author: lp
 * @CreateTime: 2024-07-24  16:25
 * @Description: TODO 生成百万条数据Excel
 * @Version: 1.0
 */
public class EasyExcelExample {
    public static void main(String[] args) {
        String fileName = "million_data.xlsx";

        // 使用EasyExcel生成Excel文件
        ExcelWriterBuilder writerBuilder = EasyExcel.write(fileName, DemoData.class);

        // 开始写入数据
        writerBuilder.sheet("Sheet1").doWrite(() -> {
            List<DemoData> dataList = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                DemoData data = new DemoData();
                data.setName("Name " + i);
                data.setAge(i);
                dataList.add(data);

                // 每当数据列表达到一定大小时，写入数据并清空列表以节省内存
                if (dataList.size() >= 10000) {
                    List<DemoData> writeData = new ArrayList<>(dataList);
                    dataList.clear();
                    return writeData;
                }
            }

            // 写入剩余的数据
            return dataList;
        });

        System.out.println("Excel file generated successfully!");
    }
}
