package com.gabriel.myframe;

import com.gabriel.myframe.tools.SqlUtils;
import org.apache.calcite.sql.parser.SqlParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyframeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void sqlParse() {
//        String sql = "select sum(b) as dd , b.c from db.d where e = x and f not in (x,d)";
//        String sql = "SELECT sum(x.dd) as xx ,2 from db.a x where id = xx and c = 'zz' group by xx order by dd limit 10 " ;
//        String sql = "SELECT sum(x.dd) as xx ,2 from db.a x where id = xx and c = 'zz' order by dd limit 10 ";
//        String sql = "SELECT sum(f) as xx,e FROM db.B left join B.dd on dd.xx=b.cc WHERE g = h";
        String sql = "SELECT sum(f) as xx,e FROM db.B as aa left join (select xx from B.dd union select xx from d.dddddd) as bdd on dd.xx=b.cc WHERE g = h";
//        String sql = "SELECT sum(x.dd) as xx ,2 from db.a x where id = xx and c = 'zz'  " +
//                "union all SELECT sum(f) as xx,e FROM db.B left join B.dd on dd.xx=b.cc WHERE g = h";
//        String sql = "select * from table_name where id = ? and name = ? order by name";
        try {
            SqlUtils.getSqlInfo(sql);
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}
