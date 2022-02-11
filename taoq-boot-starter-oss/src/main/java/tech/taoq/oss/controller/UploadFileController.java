package tech.taoq.oss.controller;

import tech.taoq.oss.domain.db.UploadFileDO;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadInfoDto;
import tech.taoq.oss.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UploadFileController
 *
 * @author keqi
 */
@RestController
@RequestMapping("/sys/upload")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 获取文件上传信息
     *
     * @param fileName fileName
     * @return r
     */
    @PostMapping("/{fileName}")
    public UploadInfoDto uploadFile(@PathVariable String fileName) {
        return uploadFileService.uploadFile(fileName);
    }

    /**
     * 获取文件下载链接
     *
     * @param fileName fileName
     * @return r
     */
    @PostMapping("/info/{fileName}")
    public DownloadInfoDto getDownloadInfo(@PathVariable String fileName) {
        return uploadFileService.getDownloadInfo(fileName);
    }

    /**
     * 删除文件
     *
     * @param fileName fileName
     */
    @DeleteMapping("/{fileName}")
    public void deleteFile(@PathVariable String fileName) {
        uploadFileService.deleteFile(fileName);
    }

    /**
     * 接收文件上传成功的通知
     *
     * @param param param
     */
    @GetMapping("/notification")
    public void notification(@RequestBody UploadFileDO param) {
        uploadFileService.notification(param);
    }
}
