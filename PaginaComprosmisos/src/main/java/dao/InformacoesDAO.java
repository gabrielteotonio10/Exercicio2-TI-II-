package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Informacoes;

public class InformacoesDAO extends DAO {
	
	public InformacoesDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Informacoes info) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO agendamentos (descricao, data, hora_inicio, hora_termino) "
				       + "VALUES ('" + info.getDescricao() + "', '" + info.getData() + "', '"  
				       + info.getHora_inicio() + "', '" + info.getHora_termino() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Informacoes get(String descricao) {
		Informacoes info = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM agendamentos WHERE descricao = '" + descricao + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	info = new Informacoes(
	        		rs.getString("descricao"),
	        		rs.getString("data"),
	        		rs.getString("hora_inicio"),
	        		rs.getString("hora_termino")
	        	);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return info;
	}
	
	
	public List<Informacoes> get() {
		return getAll("");
	}

	
	public List<Informacoes> getOrderByDescricao() {
		return getAll("descricao");		
	}
	
	
	public List<Informacoes> getOrderByData() {
		return getAll("data");		
	}
	
	
	public List<Informacoes> getOrderByHoraInicio() {
		return getAll("hora_inicio");		
	}
	
	
	public List<Informacoes> getOrderByHoraTermino() {
		return getAll("hora_termino");		
	}
	
	
	private List<Informacoes> getAll(String orderBy) {	
		List<Informacoes> infos = new ArrayList<Informacoes>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM agendamentos" + 
			             ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Informacoes u = new Informacoes(
	        		rs.getString("descricao"),
	        		rs.getString("data"),
	        		rs.getString("hora_inicio"),
	        		rs.getString("hora_termino")
	        	);
	            infos.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return infos;
	}


	public List<Informacoes> getDescricaoQueComecaCom(String prefixo) {
		List<Informacoes> infos = new ArrayList<Informacoes>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM agendamentos WHERE descricao LIKE '" + prefixo + "%'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Informacoes u = new Informacoes(
	        		rs.getString("descricao"),
	        		rs.getString("data"),
	        		rs.getString("hora_inicio"),
	        		rs.getString("hora_termino")
	        	);
	            infos.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return infos;
	}
	
	public List<Informacoes> getByData(String data) {
	    List<Informacoes> infos = new ArrayList<Informacoes>();
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        String sql = "SELECT * FROM agendamentos WHERE data = '" + data + "'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	            Informacoes u = new Informacoes(
	                rs.getString("descricao"),
	                rs.getString("data"),
	                rs.getString("hora_inicio"),
	                rs.getString("hora_termino")
	            );
	            infos.add(u);
	        }
	        st.close();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return infos;
	}
	
	
	public boolean update(Informacoes info) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE agendamentos SET data = '" + info.getData() + "', hora_inicio = '"  
				       + info.getHora_inicio() + "', hora_termino = '" + info.getHora_termino() + "'"
					   + " WHERE descricao = '" + info.getDescricao() + "'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(String descricao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM agendamentos WHERE descricao = '" + descricao + "'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean autenticar(String data, String hora_inicio) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM agendamentos WHERE data LIKE '" + data + "' AND hora_inicio LIKE '" + hora_inicio  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}
	
	public boolean delete(int id) {
	    boolean status = false;
	    try {
	        Statement st = conexao.createStatement();
	        String sql = "DELETE FROM agendamentos WHERE id_agendamento = " + id; // Mude 'id_agendamento' para o nome da sua coluna de ID
	        System.out.println(sql);
	        st.executeUpdate(sql);
	        st.close();
	        status = true;
	    } catch (SQLException u) {
	        throw new RuntimeException(u);
	    }
	    return status;
	}
}
