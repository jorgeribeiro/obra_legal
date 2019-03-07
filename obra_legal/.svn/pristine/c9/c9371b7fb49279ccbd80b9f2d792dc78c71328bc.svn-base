package br.gov.ma.tce.obralegal.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.zkoss.util.media.Media;

public class FTPUtil {

	public static InputStream downloadArquivo2(String nomeArquivo, String nomePasta) throws Exception {

		FTPClient ftp = new FTPClient();
		InputStream in = null;
		ByteArrayOutputStream baos = null;

		try {

			ftp.connect("172.16.0.153", 10021);
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login("obralegal", "!tce123@");
			} else {
				ftp.disconnect();
				throw new Exception("Não Foi Possível Estabelecer Uma Conexão com o Servidor de Arquivos!!");
			}

			boolean exists = ftp.changeWorkingDirectory(nomePasta);

			if (exists) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				baos = new ByteArrayOutputStream();
				ftp.retrieveFile(nomeArquivo, baos);
				in = new ByteArrayInputStream(baos.toByteArray());
			}
		} finally {
			if (baos != null) {
				baos.close();
			}
			if (in != null) {
				in.close();
			}
			ftp.disconnect();
		}
		return in;
	}
	
	public static void upload2(Media media, String filename, String nomePasta) {

		FTPClient client = new FTPClient();
		InputStream fis = null;

		try {
			client.connect("172.16.0.153", 10021);
			client.login("obralegal", "!tce123@");

			fis = media.getStreamData();			
			boolean exists = client.changeWorkingDirectory(nomePasta);

			if (!exists) {
				client.mkd(nomePasta);
				client.changeWorkingDirectory(nomePasta);
			}

			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.storeFile(filename, fis);
			client.logout();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void removeArquivo(String nomeArquivo, String nomePasta) {

		FTPClient client = new FTPClient();
		try {
			client.connect("172.16.0.153", 10021);
			client.login("obralegal", "!tce123@");
			client.changeWorkingDirectory(nomePasta);
			client.removeDirectory(nomePasta);
			client.deleteFile(nomeArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
