import com.td.aocp.aocpg.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

class FileOperationsTest {
    @Mock
    private FileService fileService;
    @Mock
    private Logger logger;
    @InjectMocks
    private FileOperations fileOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(fileOperations, "LOGGER", logger);
    }

    @Test
    void testFileOperationsScheduled() {
        fileOperations.fileOperationsScheduled();
        verify(logger).info("Deleting files scheduled job started");
        verify(fileService).deleteFolders();
        verify(logger).info("Loading files scheduled job started");
        verify(fileService).copyAllFolders();
    }
}