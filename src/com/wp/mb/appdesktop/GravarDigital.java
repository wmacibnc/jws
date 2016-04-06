package com.wp.mb.appdesktop;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import com.nitgen.SDK.BSP.NBioBSPJNI;

public class GravarDigital {

	public static void main(String[] argumentos) {

		GravarDigital app = new GravarDigital();

		String hashCode = app.capturarDigital();
		String idUsuario = app.obterDadosUsuario(argumentos);
		app.enviarDados(idUsuario, hashCode);
	}

	private String obterDadosUsuario(String[] argumentos) {
		String retorno = null;
		if (argumentos.length > 0) {
			retorno = argumentos[0];
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao obter dados do usuário \n Efetue novamente o procedimento.",
					"Sistema Reviv - Biometria", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return retorno;
	}

	private String capturarDigital() {

		NBioBSPJNI bsp = new NBioBSPJNI();
		NBioBSPJNI.FIR_HANDLE hSavedFIR;
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;

		bsp.OpenDevice();

		hSavedFIR = bsp.new FIR_HANDLE();

		bsp.Capture(hSavedFIR);

		if (CheckError(bsp)) {
			JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		textSavedFIR = bsp.new FIR_TEXTENCODE();
		bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);

		if (CheckError(bsp)) {
			JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		JOptionPane.showMessageDialog(null, "Sucesso! \n Biometria Capturada com sucesso");

		return textSavedFIR.TextFIR;

	}

	private Boolean CheckError(NBioBSPJNI bsp) {
		if (bsp.IsErrorOccured()) {
			return true;
		}

		return false;
	}

	private final String USER_AGENT = "Mozilla/5.0";

	private void enviarDados(String idUsuario, String hashCode) {

		String servico = "adicionarBiometria";
		String url = Constantes.ENDERECO;

		try {
			String urlCompleta = url + servico;
			URL obj = new URL(urlCompleta);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "idUsuario=" + idUsuario + "&hashCode=" + hashCode;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + urlCompleta);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
