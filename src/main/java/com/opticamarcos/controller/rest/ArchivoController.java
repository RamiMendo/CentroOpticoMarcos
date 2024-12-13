package com.opticamarcos.controller.rest;

//@RestController
//@RequestMapping("/archivo")
public class ArchivoController {

//    @Autowired
//    private ArchivoService archivoService;
//
//    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Operation(summary = "",
//            description = "",
//            tags = {"Archivos"}
//    )
//    private String save(@RequestBody MultipartFile file) throws Exception {
//        String archivo = archivoService.saveArchivo(file);
//
//        return archivo;
//    }
//
//    @GetMapping(path = "/download")
//    @Operation(summary = "",
//            description = "",
//            tags = {"Archivos"}
//    )
//    private ResponseEntity<?> download(@RequestParam Integer id) throws Exception {
//        Archivo archivo = archivoService.getArchivo(id);
//
//        byte[] bytesArchivo = BytesUtil.decompress(archivo.getFileData(), false) ;
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.parseMediaType(archivo.getFileType()))
//                .body(bytesArchivo);
//
//    }

}
