import { Text, View } from 'react-native'
import React, { Component } from 'react'
import { SPopup, SText, SView } from 'servisofts-component'


type BtnPropsType = {
    hidden: Boolean,
    children?: any,
    color?: any,
    onPress?: () => any
}
export default class Btn extends Component<BtnPropsType> {
    constructor(props) {
        super(props);
        this.state = {}
    }

    handlePress = () => {
        if (this.props.onPress) {
            this.props.onPress();
        }
    }
    render() {
        if (this.state.hidden) return null;
        let item = this.props.children;
        if (typeof item == "string") {
            item = <SText fontSize={16} bold color={this.props.color}>{this.props.children}</SText>;
        }
        return (<SView col={"xs-12"} height={50} card center   {...this.props} onPress={!this.props.onPress ? null : this.handlePress.bind(this)}>
            {item}
        </SView>
        )
    }
}