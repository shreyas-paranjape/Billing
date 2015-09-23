package com.cybercad.billing.service.api;

import java.io.File;
import java.util.List;

public interface ExportService {

	public File printToExcel(List<List<String>> data);

}
