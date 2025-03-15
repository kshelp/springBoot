import React, { Component } from 'react';
import axios from "axios";

class floatingPopulationList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            responseFPList: '',
            append_FPList: '',
        }
    }

    componentDidMount() {
        this.callFloatPopulListApi()
    }

    callFloatPopulListApi = async () => {
            axios.get('/api/guestbook/list', {
            })
            .then( response => {
                //alert( JSON.stringify(response) );
                try {
                    this.setState({ responseFPList: response });
                    this.setState({ append_FPList: this.FloatPopulListAppend() });
                } catch (error) {
                    alert(error)
                }
            })
            .catch( error => {alert(error);return false;} );
    }

    FloatPopulListAppend = () => {
        let result = []
        var FPList = this.state.responseFPList.data
        var jsonString = JSON.stringify(FPList)
        //jsonString = jsonString.replace(/\(1시간단위\)/g, '')
        //jsonString = jsonString.replace(/\(10세단위\)/g, '')
        var json = JSON.parse(jsonString)

        for(let i=0; i<json.list.length; i++){
            var data = json.list[i]
            var idx = i+1
            result.push(
            <tr class="hidden_type">
            <td>{data.gno}</td>
            <td>{data.title}</td>
            <td>{data.content}</td>
            <td>{data.writer}</td>
            </tr>
            )
        }
        return result
    }

    render () {
        return (
            <section class="sub_wrap" >
                <article class="s_cnt mp_pro_li ct1 mp_pro_li_admin">
                    <div class="li_top">
                        <h2 class="s_tit1">Guestbook</h2>
                    </div>
                    <div class="list_cont list_cont_admin">
                        <table class="table_ty1 fp_tlist">
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>작성자</th>
                            </tr>
                        </table>	
                        <table class="table_ty2 fp_tlist">
                            {this.state.append_FPList}
                        </table>
                    </div>
                </article>
            </section>
        );
    }
}

export default floatingPopulationList;