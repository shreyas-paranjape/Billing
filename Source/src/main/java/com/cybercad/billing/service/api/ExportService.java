package com.cybercad.billing.service.api;

import java.util.List;

public interface ExportService {

	public void printToExcel(List<List<String>> data);

}
