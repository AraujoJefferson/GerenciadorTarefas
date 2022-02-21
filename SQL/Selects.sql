-- Montar select que retorne nome do departamento, quantidade de tarefas
-- finalizadas e quantidade de tarefas não finalizadas

select
	d.titulo,
	t.finalizado,
	count(*)
from
	Departamento d
inner join Tarefa t
on
	d.id = t.id_departamento
group by
	d.titulo,
	t.finalizado;

--Retornar a pessoa que mais gastou horas em janeiro de 2022

select
		t.id_pessoa ,
		sum(duracao) duracao_total
from
		tarefa t
where
	to_char(t.prazo, 'yyyy-MM') = '2022-01'
group by
		t.id_pessoa
order by
	duracao_total desc
limit 1;


--Select que retorne título da tarefa, prazo, se tiver pessoa alocada na 
--tarefa exibir como “Encaminhado para + nome do pessoa” caso contrário “Pendente” e 
--total de horas que essa pessoa já gastou. Ordenar por prazo decrescente. 
select
		t.titulo ,
	t.prazo,
	sum(t.duracao) total , 
		case
		when t.id_pessoa is not null
			then CONCAT('Encaminhado para ', (select
												p.nome
											from
												pessoa p
											where
												p.id = t.id_pessoa))
		else ('Pendente')
	end
from
		tarefa t
group by
	t.titulo,
	t.prazo,
	t.id_pessoa
order by
	t.prazo desc
