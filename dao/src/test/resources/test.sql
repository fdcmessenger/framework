declare @checkperiod varchar(6)
  declare @deptid      varchar(30) 
   declare @amount numeric(18,2)

declare  CursorA cursor for
	select deptid from t_deptkey  where  checkPeriod='201307'
open CursorA
set @checkperiod='201307'
	fetch CursorA into @deptid
	while @@Fetch_Status = 0 
	begin
	--print @deptid
	--print @checkperiod

select @amount= isnull(sum(amount),0) from v_sourcepayin 
            where checkperiod=@checkperiod and kddeptid=@deptid 
                and zxdeptid<>kddeptid
                and @deptid in (select deptid from t_department where depttype='医疗' )

update t_deptkey set FIELD025= @amount where  checkPeriod='201307' and deptid=@deptid

	fetch CursorA into @deptid
	end
	close CursorA
	deallocate CursorA


update t_deptkey set FIELD025= dbo.f_jjpayin(checkperiod,deptid) where  checkPeriod='201307'