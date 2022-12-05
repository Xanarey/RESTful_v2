package service;

import model.File;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.hibernate.HibernateFileRepoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileServiceTest {

    @Mock
    private HibernateFileRepoImpl fileRepo;
    private final FileService fileService;

    public FileServiceTest() {
        MockitoAnnotations.openMocks(this);
        this.fileService = new FileService(fileRepo);
    }

    @Test
    public void getById() {
        File file = new File();
        file.setName("file.txt");
        file.setUrl("src/main/resources/upload/file.txt");
        Mockito.when(fileRepo.getById(1L)).thenReturn(file);
        File fileS = fileService.getById(1L);

        assertNotNull(fileS);
        assertEquals("file.txt", fileS.getName());
        assertEquals("src/main/resources/upload/file.txt", fileS.getUrl());
    }

    @Test
    public void getAllFiles() {
        List<File> fileList = new ArrayList<>();
        File file1 = new File();
        file1.setName("file1.txt");
        file1.setUrl("src/main/resources/upload/file1.txt");
        File file2 = new File();
        file2.setName("file2.txt");
        file2.setUrl("src/main/resources/upload/file2.txt");
        fileList.add(file1);
        fileList.add(file2);

        Mockito.when(fileRepo.getAll()).thenReturn(fileList);
        List<File> fileListService = fileService.getAllFiles();

        assertNotNull(fileListService);
        assertEquals("file1.txt", fileListService.get(0).getName());
        assertEquals("src/main/resources/upload/file1.txt", fileListService.get(0).getUrl());
        assertEquals("file2.txt", fileListService.get(1).getName());
        assertEquals("src/main/resources/upload/file2.txt", fileListService.get(1).getUrl());
        assertEquals(2, fileListService.size());
    }

    @Test
    public void updateFile() {
        File file = new File();
        file.setName("file.txt");
        file.setUrl("src/main/resources/upload/file.txt");

        Mockito.when(fileRepo.update(file)).thenReturn(file);
        File fileSer = fileService.updateFile(file);

        assertNotNull(fileSer);
        assertEquals("file.txt", fileSer.getName());
        assertEquals("src/main/resources/upload/file.txt", fileSer.getUrl());

        fileSer.setName("newFile.txt");
        fileSer.setUrl("src/main/resources/upload/newFile.txt");

        assertNotNull(fileSer);
        assertEquals("newFile.txt", fileSer.getName());
        assertEquals("src/main/resources/upload/newFile.txt", fileSer.getUrl());
    }

    @Test
    public void createFile() {
        File file = new File();
        file.setName("file.txt");
        file.setUrl("src/main/resources/upload/file.txt");
        Mockito.when(fileRepo.insert(file)).thenReturn(file);
        File fileSer = fileService.createFile(file);

        assertNotNull(fileSer);
        assertEquals("file.txt", fileSer.getName());
        assertEquals("src/main/resources/upload/file.txt", fileSer.getUrl());
    }

    @Test
    public void deleteById() {
        Mockito.doNothing().when(fileRepo).deleteById(1L);

        fileService.deleteById(1L);
        Mockito.verify(fileRepo).deleteById(1L);
    }
}