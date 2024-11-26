package com.opticamarcos.service;

import com.opticamarcos.model.entity.Archivo;
import com.opticamarcos.repository.ArchivoRepository;
//import com.opticamarcos.util.BytesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public String saveArchivo(MultipartFile multipartFile) throws Exception {
        String nombreArchivo = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(nombreArchivo.contains(".."))
            throw new Exception("ARCHIVO CONTIENE UNA SECUENCIA DE DIRECTORIO INVALIDA!");

        try {
            Archivo archivo = new Archivo();
            //archivo.setFileData(BytesUtil.compress(multipartFile.getBytes(), Deflater.BEST_COMPRESSION, false));
            archivo.setFileName(nombreArchivo);
            archivo.setFileType(multipartFile.getContentType());
            archivoRepository.save(archivo);
            return "SE ENVIO BIEN";
        }catch (Exception e){
           //throw new Exception("Error al guardar el archivo");
            return e.getMessage();
        }

    }

    public Archivo getArchivo(Integer idArchivo) throws Exception {
        Optional<Archivo> archivo = archivoRepository.findById(idArchivo);
        if (archivo.isEmpty())
            throw new Exception("Archivo no encontrado");

        return archivo.get();
    }


}
