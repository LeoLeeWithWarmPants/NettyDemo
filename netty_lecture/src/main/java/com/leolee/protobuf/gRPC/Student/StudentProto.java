// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: gRPCStudent.proto

package com.leolee.protobuf.gRPC.Student;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_MyRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_MyResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_StudentRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_StudentResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_StudentResponseList_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_StudentResponseList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_StreamRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_StreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_leolee_protobuf_StreamResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_leolee_protobuf_StreamResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\021gRPCStudent.proto\022\023com.leolee.protobuf" +
      "\"\035\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nMyRes" +
      "ponse\022\020\n\010realName\030\001 \001(\t\"\035\n\016StudentReques" +
      "t\022\013\n\003age\030\001 \001(\005\":\n\017StudentResponse\022\014\n\004nam" +
      "e\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"T\n\023St" +
      "udentResponseList\022=\n\017studentResponse\030\001 \003" +
      "(\0132$.com.leolee.protobuf.StudentResponse" +
      "\"%\n\rStreamRequest\022\024\n\014request_info\030\001 \001(\t\"" +
      "\'\n\016StreamResponse\022\025\n\rresponse_info\030\001 \001(\t" +
      "2\244\003\n\016StudentService\022Z\n\025GetRealNameByUser" +
      "Name\022\036.com.leolee.protobuf.MyRequest\032\037.c" +
      "om.leolee.protobuf.MyResponse\"\000\022`\n\017GetSt" +
      "udentByAge\022#.com.leolee.protobuf.Student" +
      "Request\032$.com.leolee.protobuf.StudentRes" +
      "ponse\"\0000\001\022j\n\025GetStudentWapperByAge\022#.com" +
      ".leolee.protobuf.StudentRequest\032(.com.le" +
      "olee.protobuf.StudentResponseList\"\000(\001\022h\n" +
      "\027BidirectionalStreamTalk\022\".com.leolee.pr" +
      "otobuf.StreamRequest\032#.com.leolee.protob" +
      "uf.StreamResponse\"\000(\0010\001B2\n com.leolee.pr" +
      "otobuf.gRPC.StudentB\014StudentProtoP\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_leolee_protobuf_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_leolee_protobuf_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_com_leolee_protobuf_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_leolee_protobuf_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_MyResponse_descriptor,
        new String[] { "RealName", });
    internal_static_com_leolee_protobuf_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_leolee_protobuf_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_StudentRequest_descriptor,
        new String[] { "Age", });
    internal_static_com_leolee_protobuf_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_leolee_protobuf_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_StudentResponse_descriptor,
        new String[] { "Name", "Age", "City", });
    internal_static_com_leolee_protobuf_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_leolee_protobuf_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_StudentResponseList_descriptor,
        new String[] { "StudentResponse", });
    internal_static_com_leolee_protobuf_StreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_leolee_protobuf_StreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_StreamRequest_descriptor,
        new String[] { "RequestInfo", });
    internal_static_com_leolee_protobuf_StreamResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_leolee_protobuf_StreamResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_leolee_protobuf_StreamResponse_descriptor,
        new String[] { "ResponseInfo", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
