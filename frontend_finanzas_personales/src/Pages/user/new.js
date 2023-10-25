import React, { Component } from 'react'
import { SForm, SList, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component'
import Model from '../../Model'
import { Container } from '../../Components'

export default class index extends Component {
    state = {}
    componentDidMount() {

    }
    render() {
        return <SPage>
            <Container>
                <SForm
                    inputs={{
                        "email": { type: "text", label: "Email" },
                        "password": { type: "text", label: "Password" },
                    }}
                    onSubmitName={"SUBIR"}
                    onSubmit={(e)=>{
                        Model.user.registro(e).then(e=>{
                            SNavigation.goBack();
                        }).catch(e=>{

                        })
                    }}
                />

            </Container>
        </SPage>
    }
}