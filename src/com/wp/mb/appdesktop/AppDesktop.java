package com.wp.mb.appdesktop;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import com.nitgen.SDK.BSP.NBioBSPJNI.FIR_TEXTENCODE;

public class AppDesktop {

<<<<<<< HEAD
	private static final String endPoint = "autenticaBiometria/";

	public static void main(String[] argumentos) {

		AppDesktop app = new AppDesktop();
		String hashCode = app.capturarDigital();
		// String hashCodeGerado = app.validarBiometria(hashCode);
		abrirNavegador("123456");
	}
=======
    private static final String endPoint = "/autenticaBiometria/";
    // private static final String endereco = "http://localhost:8080/projetos";
    private static final String endereco = "http://www.sistemareviv.com";

    public static void main(String[] args) {
        if (args.length > 0) {
            JOptionPane.showMessageDialog(null, "Entrou... ");
        }

        // AppDesktop app = new AppDesktop();
        // String hashCode = app.capturarDigital();
        // String hashCodeGerado = app.validarBiometria(hashCode);
        // abrirNavegador(hashCodeGerado);
        //
        // app.obterListaBiometria(hashCode);
        // app.salvaHashCode(1L, hashCodeGerado);
    }
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430

    private String capturarDigital() {

        JOptionPane.showMessageDialog(null, "Capturando Digital!!! ");

        NBioBSPJNI bsp = new NBioBSPJNI();
        NBioBSPJNI.FIR_HANDLE hSavedFIR;
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;

        bsp.OpenDevice();

        hSavedFIR = bsp.new FIR_HANDLE();

<<<<<<< HEAD
		if (CheckError(bsp)) {
			JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
=======
        bsp.Capture(hSavedFIR);
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430

        if (CheckError(bsp)) {
            JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

<<<<<<< HEAD
		if (CheckError(bsp)) {
			JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
=======
        textSavedFIR = bsp.new FIR_TEXTENCODE();
        bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430

        if (CheckError(bsp)) {
            JOptionPane.showMessageDialog(null, "Erro ao capturar", "Sistema Reviv - Acesso central",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        JOptionPane.showMessageDialog(null, "Sucesso! \n Biometria Capturada com sucesso");

        return textSavedFIR.TextFIR;

    }

    private String validarBiometria(String hashCode) {
        String retorno = null;
        JOptionPane.showMessageDialog(null, "Validando Digital");

        NBioBSPJNI bsp = new NBioBSPJNI();
        bsp.OpenDevice();

        NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();
        NBioBSPJNI.INPUT_FIR inputFIR2 = bsp.new INPUT_FIR();

        Boolean bResult = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD payload = bsp.new FIR_PAYLOAD();

        FIR_TEXTENCODE textSavedFIR = bsp.new FIR_TEXTENCODE();

        // Digital existente
        textSavedFIR.TextFIR = hashCode;
        inputFIR.SetTextFIR(textSavedFIR);

        // lendo digital para verificar
        bsp.Capture(bsp.new FIR_HANDLE());
        inputFIR2.SetTextFIR(textSavedFIR);

<<<<<<< HEAD
		if (bResult) {
			JOptionPane.showMessageDialog(null, "Validado ");
			retorno = "12345678";
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível validar suas credenciais",
					"Sistema Reviv - Acesso central", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
=======
        // bsp.Verify(inputFIR, bResult, payload);
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430

        bsp.VerifyMatch(inputFIR, inputFIR2, bResult, payload);

<<<<<<< HEAD
	public Boolean CheckError(NBioBSPJNI bsp) {
		if (bsp.IsErrorOccured()) {
			return true;
		}
=======
        if (bResult) {
            JOptionPane.showMessageDialog(null, "Validado " + payload.GetText());
            retorno = "12345678";
        } else {
            JOptionPane.showMessageDialog(null, "Nï¿½o foi possï¿½vel validar suas credenciais",
                    "Sistema Reviv - Acesso central", JOptionPane.ERROR_MESSAGE);
        }
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430

        return retorno;
    }

<<<<<<< HEAD
	private static void abrirNavegador(String hashCodeGerado) {
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		URI uri = null;
		try {
			uri = new URI(Constantes.ENDERECO + endPoint + hashCodeGerado);
			desktop.browse(uri);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
	}
=======
    public Boolean CheckError(NBioBSPJNI bsp) {
        if (bsp.IsErrorOccured()) {
            JOptionPane.showMessageDialog(null, "Erro ao capturar: " + bsp.GetErrorCode(),
                    "Sistema Reviv - Acesso central", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private static void abrirNavegador(String hashCodeGerado) {
        Desktop desktop = null;
        desktop = Desktop.getDesktop();
        URI uri = null;
        try {
            uri = new URI(endereco + endPoint + hashCodeGerado);
            desktop.browse(uri);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }

    private Set<String> obterListaBiometria(String hashCode) {

        Set<String> lista = new HashSet<>();

        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("select biometria from Usuario ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("biometria"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private void salvaHashCode(Long idUsuario, String hashCode) {

        Connection con = new ConnectionFactory().getConnection();

        String sql = "insert into biometria" + " (idUsuario, hashCode)" + " values (?,?) ";
        PreparedStatement stmt;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);

            stmt.setLong(1, idUsuario);
            stmt.setString(2, hashCode);

            stmt.execute();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Gravado!");

    }

}

class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/reviv", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> cd1972859b021a0dd2d894ded89f843735686430
}
