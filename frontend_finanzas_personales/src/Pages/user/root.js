import React, { Component } from 'react'
import { SHr, SPage } from 'servisofts-component'
import { Container, Link } from '../../Components'

export default class root extends Component {
    render() {
        return <SPage>
            <Container>
                <SHr />
                <Link src={"/user/list"} />
                <SHr />
                <Link src={"/user/new"} />
                
            </Container>
        </SPage>
    }
}