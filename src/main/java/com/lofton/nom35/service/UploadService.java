/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.service;

import com.lofton.nom35.util.UploadUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author cadri
 */
@Service
public class UploadService {
    
     private final UploadUtil uploadUtil;

    @Autowired
    public UploadService(UploadUtil uploadUtil) {
        this.uploadUtil = uploadUtil;
    }

    public List<Map<String, String>> upload(MultipartFile file) throws IOException {

        DataFormatter formatter = new DataFormatter();

        Path tempDir = Files.createTempDirectory("");

        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();

        file.transferTo(tempFile);

        Workbook workbook = WorkbookFactory.create(tempFile);

        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        Sheet sheet = workbook.getSheetAt(0);

        Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);

        Row headerRow = rowStreamSupplier.get().findFirst().get();
        List<String> headerCells = uploadUtil.getStream(headerRow)
                .map(Cell::getStringCellValue)
                .collect(Collectors.toList());

        int colCount = headerCells.size();

        List<Map<String, String>> content = rowStreamSupplier.get()
                .skip(1)
                .map(row
                        -> {
                    List<String> cellList = uploadUtil.getStream(row)
                            .map((cell) -> formatter.formatCellValue(cell, evaluator))
                            .collect(Collectors.toList());
//                    System.out.println(uploadUtil.getStream(row)
//                            .map((cell) -> formatter.formatCellValue(cell, evaluator)).collect(Collectors.toMap(cell, )));
                    for (int i = 0; i < cellList.size(); i++) {

                        if ("".equals(cellList.get(i)) || cellList.get(i) == null) {
                            cellList.set(i, "vacio");
                        }
                    }

                    return uploadUtil.cellIteratorSupplier(colCount)
                            .get()
                            .collect(Collectors.toMap(headerCells::get, cellList::get)
                            );
                })
                .collect(Collectors.toList());

        return content;

    }

    
}
